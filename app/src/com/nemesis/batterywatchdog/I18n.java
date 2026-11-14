package com.nemesis.batterywatchdog;

public class I18n {
    public static final int LANG_EN = 0;
    public static final int LANG_AR = 1;

    public static int currentLang = LANG_EN;

    public static boolean isAr() {
        return currentLang == LANG_AR;
    }

    public static String t(String en, String ar) {
        return currentLang == LANG_AR ? ar : en;
    }

    // App & Navigation
    public static String appTitle() { return t("Battery Watchdog", "مراقب البطارية"); }
    public static String settings() { return t("Settings ›", "الإعدادات ›"); }
    public static String back() { return t("‹ Back", "‹ رجوع"); }
    public static String settingsTitle() { return t("Settings", "الإعدادات"); }
    public static String autoSaveHint() { return t("Changes save by themselves and apply from the next drive.", "تُحفظ التغييرات تلقائياً وتُطبق من القيادة القادمة."); }
    public static String notSaved(String err) { return t("Not saved: " + err, "لم يتم الحفظ: " + err); }
    public static String saved() { return t("Saved. Applies from the next drive.", "تم الحفظ. يُطبق بدءاً من القيادة القادمة."); }

    // Cards
    public static String cardLanguage() { return t("Language", "اللغة"); }
    public static String cardWarnings() { return t("Warnings", "التنبيهات"); }
    public static String cardSensor() { return t("Sensor", "الحساس"); }
    public static String cardTools() { return t("Tools", "الأدوات"); }

    // Language settings
    public static String selectLanguage() { return t("Language", "لغة التطبيق"); }

    // Warnings settings
    public static String monitor12v() { return t("Monitor the 12V battery", "مراقبة بطارية 12V"); }
    public static String optOn() { return t("On", "تشغيل"); }
    public static String optOff() { return t("Off", "إيقاف"); }
    public static String warnStartsBelow() { return t("Warn when it starts below", "تنبيه عند بدء التشغيل بأقل من"); }
    public static String warnDropsParked() { return t("Warn when it drops this much while parked", "تنبيه عند انخفاض الشحن أثناء التوقف بمقدار"); }
    public static String unitPts() { return t(" pts", " نقطة"); }
    public static String popupOnStart() { return t("Pop-up when the car starts", "إشعار منبثق عند تشغيل السيارة"); }
    public static String popWarningsOnly() { return t("Warnings only", "التنبيهات فقط"); }
    public static String popEveryStart() { return t("Every start", "مع كل تشغيل"); }

    // Sensor settings
    public static String readEvery() { return t("Read every (screen on)", "معدل القراءة (والشاشة تعمل)"); }
    public static String min1() { return t("1 min", "1 دقيقة"); }
    public static String min5() { return t("5 min", "5 دقائق"); }
    public static String min15() { return t("15 min", "15 دقيقة"); }
    public static String decodeValueAs() { return t("Decode the value as", "فك تشفير القيمة كـ"); }
    public static String decodeAuto() { return t("Auto", "تلقائي"); }
    public static String decodeInteger() { return t("Integer", "عدد صحيح"); }
    public static String decodeFloat() { return t("Float", "كسر عشري"); }
    public static String scale() { return t("Scale", "المقياس"); }
    public static String scale1() { return t("1 raw = 1 %", "1 خام = 1 %"); }
    public static String scale05() { return t("1 raw = 0.5 %", "1 خام = 0.5 %"); }
    public static String readSensorNow() { return t("Read sensor now", "قراءة الحساس الآن"); }

    // Tools
    public static String batteryReport() { return t("Battery report…", "تقرير البطارية…"); }
    public static String warningHistory() { return t("Warning history", "سجل التنبيهات"); }
    public static String diagnostics() { return t("Diagnostics", "التشخيص"); }
    public static String exportToUsb() { return t("Export to USB", "تصدير إلى USB"); }
    public static String shareData() { return t("Share data…", "مشاركة البيانات…"); }
    public static String restartMonitor() { return t("Restart monitor", "إعادة تشغيل المراقب"); }
    public static String testRun10s() { return t("Test run (10 s)", "تشغيل تجريبي (10 ثوان)"); }
    public static String clearHistory() { return t("Clear history…", "مسح السجل…"); }
    public static String unverifiedOverride() { return t("Unverified firmware: use built-in numbers…", "فيرموير غير معتمد: استخدام الأرقام المدمجة…"); }

    // Home Tiles
    public static String tileParked() { return t("Parked before this drive", "فترة التوقف قبل هذه الرحلة"); }
    public static String tileDrain() { return t("Parked drain, last 7 days", "استنزاف التوقف (آخر 7 أيام)"); }
    public static String tileLowest() { return t("Lowest start, last 7 days", "أدنى نسبة تشغيل (آخر 7 أيام)"); }
    public static String tileHealth() { return t("HV battery health", "صحة البطارية الرئيسية (HV)"); }

    // Status & States
    public static String statusGood() { return t("Good", "جيدة"); }
    public static String statusFair() { return t("Fair", "متوسطة"); }
    public static String statusLow() { return t("Low", "منخفضة"); }
    public static String statusOK() { return t("OK", "جيد"); }
    public static String statusCritical() { return t("Critical", "حرجة"); }
    public static String statusCharging() { return t("Charging", "جارِ الشحن"); }
    public static String statusUnknown() { return t("Unknown", "غير معروف"); }
    public static String statusPaused() { return t("Paused", "متوقف مؤقتاً"); }
    public static String statusOff() { return t("Off", "إيقاف"); }
    public static String statusMonitorOff() { return t("Monitor off", "المراقب متوقف"); }
    public static String statusNoReading() { return t("No reading", "لا توجد قراءة"); }
    public static String nothingRead() { return t("Nothing is read from the car.", "لم يتم قراءة أي بيانات من السيارة."); }
    public static String noReadingYet() { return t("No reading yet. If this lasts, see Settings → Tools → Diagnostics.", "لا توجد قراءة بعد. إذا استمر هذا، راجع الإعدادات ← الأدوات ← التشخيص."); }
    public static String showsAfterPark() { return t("Shows after a park", "يظهر بعد التوقف"); }
    public static String noDrivesYet() { return t("No drives yet", "لا توجد رحلات بعد"); }
    public static String notEnoughParkedTime() { return t("Not enough parked time yet", "وقت التوقف غير كافٍ بعد"); }
    public static String notAvailable() { return t("Not available", "غير متوفر"); }
    public static String noChange() { return t("No change", "بدون تغيير"); }
    public static String mainBattery() { return t("Main battery", "البطارية الرئيسية"); }
    public static String outside() { return t("Outside", "الحرارة الخارجية"); }

    // Chart Legend
    public static String last30Drives() { return t("Last 30 drives      ", "آخر 30 رحلة      "); }
    public static String legendStart() { return t(" start   ", " بدء   "); }
    public static String legendEnd() { return t(" end   ", " نهاية   "); }
    public static String legendParked() { return t(" parked   ", " توقف   "); }
    public static String legendRecharged() { return t(" recharged   ", " شحن   "); }
    public static String legendWarnBelow(int pct) { return t(" warn below " + pct + "%", " تنبيه بأقل من " + pct + "%"); }

    // Dialogs & Prompts
    public static String clearTitle() { return t("Clear history?", "مسح السجل؟"); }
    public static String clearMsg() { return t("This deletes every logged reading, drive and warning on this car. Settings are kept. If you might share your data later, export it to USB first.", "سيؤدي هذا إلى حذف جميع القراءات والرحلات والتنبيهات المسجلة. سيتم الاحتفاظ بالإعدادات. إذا كنت تنوي مشاركة بياناتك، قم بتصديرها إلى USB أولاً."); }
    public static String btnClear() { return t("Clear", "مسح"); }
    public static String btnCancel() { return t("Cancel", "إلغاء"); }
    public static String btnClose() { return t("Close", "إغلاق"); }
    public static String btnExport() { return t("Export", "تصدير"); }
    public static String btnNoThanks() { return t("No thanks", "لا، شكراً"); }
}
