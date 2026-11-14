#!/system/bin/sh
# preinstall.sh - Verifies vehicle compatibility before installing Battery Watchdog.
# If compatible: exits 0 (NEMESIS proceeds with installation).
# If incompatible: exits 1 (NEMESIS aborts installation).

export PATH=/data/nemesis/bin:/sbin:/vendor/bin:/system/sbin:/system/bin:/system/xbin:$PATH

STATE=/data/nemesis/state/battery.watchdog
LOG_DIR=/data/nemesis/logs/battery.watchdog
mkdir -p "$STATE" "$LOG_DIR" 2>/dev/null
PRELOG="$LOG_DIR/preinstall.log"

log() {
    echo "$*"
    echo "$*" >> "$PRELOG" 2>/dev/null
}

echo "==========================================================" > "$PRELOG" 2>/dev/null
log "=== Battery Watchdog: Verifying Vehicle Compatibility ==="
log "=== التحقق من توافق نظام السيارة مع Battery Watchdog ==="
log "Date: $(date 2>/dev/null)"
log "Firmware: $(getprop ro.build.version.incremental 2>/dev/null)"
log "Product: $(getprop ro.product.model 2>/dev/null)"
log "=========================================================="

# -------------------------------------------------------------
# Test 1: Check if original telematics HSCAN service exists
# اختبار 1: التحقق من وجود خدمة التيليماتكس الأصلية في النظام
# -------------------------------------------------------------
log "Checking for telematics service (tm_hscan_service)..."
SVC="tm_hscan_service"
IFACE="com.hkmc.telematics.hscan.tm_interface.ITM_HSCANService"

SVC_FOUND=0
if service list 2>/dev/null | grep -F "$SVC: [$IFACE]" >/dev/null 2>&1; then
    SVC_FOUND=1
elif service check "$SVC" 2>/dev/null | grep -q "found"; then
    SVC_FOUND=1
fi

if [ "$SVC_FOUND" -ne 1 ]; then
    log "=========================================================="
    log "[ERROR] Incompatible vehicle! Service '$SVC' not found."
    log "[خطأ] نظام السيارة غير متوافق: خدمة '$SVC' غير متوفرة."
    log "Battery Watchdog requires Hyundai/Kia Gen5W telematics ($IFACE)."
    log "يتطلب التطبيق وجود خدمة التيليماتكس الأصلية في شاشات هيونداي/كيا Gen5W."
    log "Installation aborted! / تم إلغاء عملية التثبيت."
    log "=========================================================="
    exit 1
fi
log "[OK] Found telematics service: $SVC"

# -------------------------------------------------------------
# Test 2: Check for required functions (getBatSoc)
# اختبار 2: التحقق من توفر الدوال المطلوبة لقراءة البطارية (getBatSoc)
# -------------------------------------------------------------
log "Checking for required functions (getBatSoc)..."

SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PKG_DIR=$(cd "$SCRIPT_DIR/.." && pwd)
APK="$PKG_DIR/app/batterywatchdog.apk"
KNOWN_FILE="$PKG_DIR/known_firmware.txt"
FW=$(getprop ro.build.version.incremental 2>/dev/null)

FUNC_FOUND=0
RESOLVED_SOURCE=""
SOC_NUM=""
HV_NUM=""
SOH_NUM=""

# Method A: Check known_firmware.txt cache
if [ -n "$FW" ] && [ -r "$KNOWN_FILE" ]; then
    while read -r _f _s _h _o _rest; do
        case "$_f" in ''|'#'*) continue;; esac
        if [ "$_f" = "$FW" ]; then
            FUNC_FOUND=1
            RESOLVED_SOURCE="known_firmware.txt"
            SOC_NUM=$_s
            HV_NUM=$_h
            SOH_NUM=$_o
            log "[OK] Matched verified firmware entry: $FW (soc=$_s, hv=$_h, soh=$_o)"
            break
        fi
    done < "$KNOWN_FILE"
fi

# Method B: Run TxnResolver via dalvikvm if not already resolved
if [ "$FUNC_FOUND" -ne 1 ] && [ -x /system/bin/dalvikvm ] && [ -f "$APK" ]; then
    log "Running dynamic bytecode resolver (TxnResolver)..."
    mkdir -p /data/local/tmp/dalvik-cache 2>/dev/null
    chmod 777 /data/local/tmp/dalvik-cache 2>/dev/null
    export ANDROID_DATA=/data/local/tmp
    
    RESOLVE_OUT=$(/system/bin/dalvikvm -cp "$APK" com.nemesis.batterywatchdog.TxnResolver --write-state 2>&1)
    RESOLVE_CODE=$?
    log "$RESOLVE_OUT"
    if [ $RESOLVE_CODE -eq 0 ] && [ -r "$STATE/txn" ]; then
        FUNC_FOUND=1
        RESOLVED_SOURCE="TxnResolver (dalvikvm)"
        log "[OK] Dynamic transaction resolver succeeded."
    fi
fi

# Method C: Shell scan fallback across system framework/app files
if [ "$FUNC_FOUND" -ne 1 ]; then
    log "Running shell framework scan for ITM_HSCANService and getBatSoc..."
    
    SEARCH_FILES="/system/framework/com.hkmc.ext.hcan.odex /system/framework/com.hkmc.ext.hcan.jar"
    
    check_needles() {
        _file="$1"
        if command -v strings >/dev/null 2>&1; then
            strings "$_file" 2>/dev/null | grep -q "ITM_HSCANService" && \
            strings "$_file" 2>/dev/null | grep -q "getBatSoc"
        else
            grep -q -s "ITM_HSCANService" "$_file" 2>/dev/null && \
            grep -q -s "getBatSoc" "$_file" 2>/dev/null
        fi
    }
    
    MATCHED_FILE=""
    for sf in $SEARCH_FILES; do
        if [ -f "$sf" ] && check_needles "$sf"; then
            MATCHED_FILE="$sf"
            break
        fi
    done
    
    if [ -z "$MATCHED_FILE" ]; then
        for d in /system/framework /system/app /system/priv-app /vendor/framework; do
            [ -d "$d" ] || continue
            for f in "$d"/*.odex "$d"/*.dex "$d"/*.jar "$d"/*.apk; do
                [ -f "$f" ] || continue
                if check_needles "$f"; then
                    MATCHED_FILE="$f"
                    break 2
                fi
            done
        done
    fi
    
    if [ -n "$MATCHED_FILE" ]; then
        FUNC_FOUND=1
        RESOLVED_SOURCE="shell_scan ($MATCHED_FILE)"
        log "[OK] Found ITM_HSCANService and getBatSoc in $MATCHED_FILE"
    fi
fi

# -------------------------------------------------------------
# Decision: Proceed or Abort
# القرار: إتمام التثبيت أو إلغاؤه فوراً
# -------------------------------------------------------------
if [ "$FUNC_FOUND" -ne 1 ]; then
    log "=========================================================="
    log "[ERROR] Incompatible vehicle! Function 'getBatSoc' not found."
    log "[خطأ] نظام السيارة غير متوافق: لم يتم العثور على دالة قراءة البطارية (getBatSoc)."
    log "Battery Watchdog cannot read 12V battery on this system."
    log "لا يمكن للتطبيق قراءة بيانات بطارية 12V على هذا النظام."
    log "Installation aborted! / تم إلغاء عملية التثبيت."
    log "=========================================================="
    exit 1
fi

# If resolved via Method A and $STATE/txn doesn't exist, write it now
if [ "$RESOLVED_SOURCE" = "known_firmware.txt" ] && [ -n "$SOC_NUM" ]; then
    {
        echo "fw=$FW"
        echo "soc=$SOC_NUM"
        [ -n "$HV_NUM" ] && [ "$HV_NUM" != "-" ] && echo "hv=$HV_NUM"
        [ -n "$SOH_NUM" ] && [ "$SOH_NUM" != "-" ] && echo "soh=$SOH_NUM"
        echo "ext=1"
        echo "source=known_firmware.txt"
    } > "$STATE/txn" 2>/dev/null
fi

log "=========================================================="
log "[SUCCESS] Vehicle verified compatible! / تم التحقق من توافق السيارة بنجاح!"
log "Verified source: $RESOLVED_SOURCE"
log "Proceeding with installation... / جاري متابعة التثبيت..."
log "=========================================================="
exit 0
