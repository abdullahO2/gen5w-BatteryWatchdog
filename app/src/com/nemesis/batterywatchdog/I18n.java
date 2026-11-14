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
    public static String autoSaveHint() { return t("Changes save by themselves and apply from the next drive.", "تُحفظ الإعدادات تلقائياً وتُطبق بدءاً من الرحلة القادمة."); }
    public static String notSaved(String err) { return t("Not saved: " + err, "لم يتم الحفظ: " + err); }
    public static String saved() { return t("Saved. Applies from the next drive.", "تم الحفظ. يُطبق بدءاً من الرحلة القادمة."); }

    // Cards
    public static String cardWarnings() { return t("General & Warnings", "الإعدادات العامة والتنبيهات"); }
    public static String cardSensor() { return t("Sensor & Reading", "إعدادات الحساس والقراءة"); }
    public static String cardTools() { return t("Tools & Diagnostics", "الأدوات والتشخيص"); }

    // Language & Temperature unit settings
    public static String selectLanguage() { return t("Language", "لغة التطبيق"); }
    public static String tempUnitTitle() { return t("Temperature unit", "وحدة قياس الحرارة"); }
    public static String tempC() { return t("°C (Celsius)", "°C (مئوية)"); }
    public static String tempF() { return t("°F (Fahrenheit)", "°F (فهرنهايت)"); }

    // Warnings settings
    public static String monitor12v() { return t("Monitor the 12V battery", "مراقبة بطارية 12V"); }
    public static String optOn() { return t("On", "تشغيل"); }
    public static String optOff() { return t("Off", "إيقاف"); }
    public static String warnStartsBelow() { return t("Warn when it starts below", "تنبيه إذا كان الشحن عند بدء التشغيل أقل من"); }
    public static String warnDropsParked() { return t("Warn when it drops this much while parked", "تنبيه عند انخفاض الشحن أثناء التوقف بمقدار"); }
    public static String unitPts() { return t(" pts", " نقطة"); }
    public static String popupOnStart() { return t("Pop-up when the car starts", "إشعار منبثق على الشاشة عند التشغيل"); }
    public static String popWarningsOnly() { return t("Warnings only", "التنبيهات فقط"); }
    public static String popEveryStart() { return t("Every start", "مع كل تشغيل"); }

    // Sensor settings
    public static String readEvery() { return t("Read every (screen on)", "معدل القراءة (والشاشة تعمل)"); }
    public static String min1() { return t("1 min", "1 دقيقة"); }
    public static String min5() { return t("5 min", "5 دقائق (موصى به)"); }
    public static String min15() { return t("15 min", "15 دقيقة"); }
    public static String decodeValueAs() { return t("Decode the value as", "فك تشفير القيمة كـ"); }
    public static String decodeAuto() { return t("Auto", "تلقائي"); }
    public static String decodeInteger() { return t("Integer", "عدد صحيح (Integer)"); }
    public static String decodeFloat() { return t("Float", "كسر عشري (Float)"); }
    public static String scale() { return t("Scale", "المقياس (Scale)"); }
    public static String scale1() { return t("1 raw = 1 %", "1 خام = 1 %"); }
    public static String scale05() { return t("1 raw = 0.5 %", "1 خام = 0.5 %"); }
    public static String readSensorNow() { return t("Read sensor now", "قراءة الحساس الآن"); }

    // Tools
    public static String batteryReport() { return t("Battery report…", "تقرير حالة البطارية…"); }
    public static String warningHistory() { return t("Warning history", "سجل التنبيهات"); }
    public static String diagnostics() { return t("Diagnostics", "تشخيص النظام"); }
    public static String exportToUsb() { return t("Export to USB", "تصدير البيانات إلى USB"); }
    public static String shareData() { return t("Share data…", "مشاركة البيانات…"); }
    public static String restartMonitor() { return t("Restart monitor", "إعادة تشغيل خدمة المراقبة"); }
    public static String testRun10s() { return t("Test run (10 s)", "فحص تجريبي (10 ثوان)"); }
    public static String clearHistory() { return t("Clear history…", "مسح سجل البيانات…"); }
    public static String unverifiedOverride() { return t("Unverified firmware: use built-in numbers…", "فيرموير غير معتمد: استخدام الأرقام المدمجة…"); }

    // Home Tiles
    public static String tileParked() { return t("Parked before this drive", "استهلاك التوقف قبل هذه الرحلة"); }
    public static String tileDrain() { return t("Parked drain, last 7 days", "استهلاك البطارية أثناء التوقف (آخر 7 أيام)"); }
    public static String tileLowest() { return t("Lowest start, last 7 days", "أدنى مستوى شحن عند التشغيل (آخر 7 أيام)"); }
    public static String tileHealth() { return t("HV battery health", "صحة البطارية الرئيسية (HV SOH)"); }

    // Status & States
    public static String statusGood() { return t("Good", "ممتازة"); }
    public static String statusFair() { return t("Fair", "جيدة"); }
    public static String statusLow() { return t("Low", "منخفضة"); }
    public static String statusOK() { return t("OK", "طبيعي"); }
    public static String statusCritical() { return t("Critical", "حرجة"); }
    public static String statusCharging() { return t("Charging", "جارٍ الشحن"); }
    public static String statusUnknown() { return t("Unknown", "غير محدد"); }
    public static String statusPaused() { return t("Paused", "متوقف مؤقتاً"); }
    public static String statusOff() { return t("Off", "إيقاف"); }
    public static String statusMonitorOff() { return t("Monitor off", "المراقب متوقف"); }
    public static String statusNoReading() { return t("No reading", "لا توجد قراءة"); }
    public static String nothingRead() { return t("Nothing is read from the car.", "لم يتم قراءة أي بيانات من السيارة بعد."); }
    public static String noReadingYet() { return t("No reading yet. If this lasts, see Settings → Tools → Diagnostics.", "لا توجد قراءة حتى الآن. إذا استمر هذا، راجع الإعدادات ← الأدوات ← تشخيص النظام."); }
    public static String showsAfterPark() { return t("Shows after a park", "يظهر بعد التوقف"); }
    public static String noDrivesYet() { return t("No drives yet", "لا توجد رحلات بعد"); }
    public static String notEnoughParkedTime() { return t("Not enough parked time yet", "مدة التوقف غير كافية للحساب بعد"); }
    public static String notAvailable() { return t("Not available", "غير متوفر"); }
    public static String noChange() { return t("No change", "بدون تغيير"); }
    public static String mainBattery() { return t("Main battery", "البطارية الرئيسية"); }
    public static String outside() { return t("Outside", "الحرارة الخارجية"); }

    // Chart Legend
    public static String last30Drives() { return t("Last 30 drives      ", "آخر 30 رحلة      "); }
    public static String legendStart() { return t(" start   ", " بدء الرحلة   "); }
    public static String legendEnd() { return t(" end   ", " نهاية الرحلة   "); }
    public static String legendParked() { return t(" parked   ", " أثناء التوقف   "); }
    public static String legendRecharged() { return t(" recharged   ", " تم الشحن   "); }
    public static String legendWarnBelow(int pct) { return t(" warn below " + pct + "%", " تنبيه بأقل من " + pct + "%"); }

    // Dialogs & Prompts
    public static String clearTitle() { return t("Clear history?", "مسح سجل البيانات؟"); }
    public static String clearMsg() { return t("This deletes every logged reading, drive and warning on this car. Settings are kept. If you might share your data later, export it to USB first.", "سيؤدي هذا إلى مسح كافة القراءات وسجلات الرحلات والتنبيهات المسجلة. سيتم الإبقاء على الإعدادات. إذا كنت تنوي مشاركة بياناتك لاحقاً، يفضل تصديرها إلى USB أولاً."); }
    public static String btnClear() { return t("Clear", "مسح السجل"); }
    public static String btnCancel() { return t("Cancel", "إلغاء"); }
    public static String btnClose() { return t("Close", "إغلاق"); }
    public static String btnExport() { return t("Export", "تصدير"); }
    public static String btnSaveToUsb() { return t("Save to USB", "حفظ إلى USB"); }
    public static String btnShareViaUsb() { return t("Share via USB", "مشاركة عبر USB"); }
    public static String btnShowMeHow() { return t("Show me how", "عرض الخطوات"); }
    public static String btnNoThanks() { return t("No thanks", "لا، شكراً"); }
    public static String btnUseBuiltin() { return t("I've checked, use them", "نعم، استخدام الأرقام المدمجة"); }
    public static String btnForSharing() { return t("For sharing, dates hidden", "للمشاركة (إخفاء التواريخ)"); }

    public static String exportDialogMsg() {
        return t("Export writes a folder you can open in Excel or Google Sheets, and a zip of the same files, ready to attach to a \"Share battery data\" issue.\n\n\"For sharing, dates hidden\" writes only a zip with the battery log, with every date moved back by the same random number of weeks. Use it if you'd rather not show when you drive.",
                 "خيار \"تصدير\" ينشئ مجلداً على الفلاشة بملفات CSV قابلة للفتح في Excel، بالإضافة لملف zip جاهز للمشاركة.\n\nخيار \"للمشاركة (إخفاء التواريخ)\" ينشئ ملف zip فقط مع تعديل التواريخ عشوائياً لحماية خصوصيتك وعدم إظهار أوقات قيادتك.");
    }
    public static String shareStepsTitle() { return t("Share your data (optional)", "مشاركة البيانات (اختياري)"); }
    public static String shareStepsMsg() {
        return t("1. Drive as usual for a few weeks. The longer the log, the more useful it is.\n"
                + "2. Insert a USB drive and tap Export below (or Settings › Tools › Export to USB). Choose "
                + "\"For sharing, dates hidden\" if you'd rather not show when you drive.\n"
                + "3. On a computer or phone, go to github.com/abdullahO2/gen5w-BatteryWatchdog, open Issues › "
                + "New issue › Share battery data, fill in what you know about your 12V battery, and attach "
                + "the .zip from the drive.\n\n"
                + "If your battery fails or is replaced later, a comment on that issue helps a lot.\n\n"
                + "An export has no location, VIN or account details.",
                "1. قم بقيادة السيارة كالمعتاد لعدة أسابيع (كلما زادت مدة السجل، زادت فائدة البيانات).\n"
                + "2. قم بتوصيل فلاشة USB واضغط على \"تصدير\" بالأسفل (أو الإعدادات ← الأدوات ← تصدير إلى USB)، ثم اختر "
                + "\"للمشاركة (إخفاء التواريخ)\" لحماية خصوصيتك.\n"
                + "3. من جهاز الكمبيوتر أو الجوال، انتقل لصفحة المشروع على GitHub، وافتح تذكرة جديدة (Issue)، ثم أرفق "
                + "ملف .zip من الفلاشة مع معلومات بطاريتك.\n\n"
                + "لا يحتوي ملف التصدير على أي بيانات موقع أو رقم هيكل (VIN) أو بيانات حساب.");
    }
    public static String testRunTitle() { return t("Test run", "فحص تجريبي"); }
    public static String noOutput() { return t("(no output)", "(لا توجد مخرجات)"); }
    public static String noReadingsToExport() { return t("There are no readings to export yet.", "لا توجد قراءات لتصديرها حتى الآن."); }

    public static String exportPrompt() {
        return t("This copies your logged readings, drives and warnings to the USB drive as spreadsheet-ready CSV files (readings.csv, drives.csv, warnings.csv) and a summary. Files already on the USB are not touched.",
                 "سيقوم هذا بنسخ سجلات قراءات البطارية والرحلات والتنبيهات إلى فلاشة USB بصيغة ملفات CSV جاهزة للفتح في Excel، مع تقرير ملخص. لن يتم المساس بالملفات الأخرى على الفلاشة.");
    }
    public static String exporting() { return t("Exporting…", "جاري التصدير إلى USB…"); }
    public static String makingReport() { return t("Making the report…", "جاري إنشاء تقرير البطارية…"); }
    public static String batteryReportTitle() { return t("Battery report", "تقرير حالة البطارية"); }
    public static String historyCleared() { return t("History cleared", "تم مسح سجل البيانات بنجاح"); }
    public static String monitorRestarted() { return t("Monitor restarted. Check Diagnostics in 30 seconds.", "تمت إعادة تشغيل المراقب. تحقق من التشخيص بعد 30 ثانية."); }
    public static String testRunning() { return t("Running the monitor for about 10 seconds…", "جاري تشغيل المراقب تجريبياً لمدة 10 ثوان…"); }
    public static String testRunNotVerified() { return t("Not verified on this firmware, so the test run is disabled.", "لم يتم التحقق من الفيرموير، لذا تم تعطيل التشغيل التجريبي."); }
    public static String noWarningsSoFar() { return t("No warnings so far.", "لا توجد تنبيهات مسجلة حتى الآن."); }
    public static String noUsb() { return t("No USB drive found. Insert a FAT32 or exFAT drive and try again.", "لم يتم العثور على فلاشة USB. يرجى توصيل فلاشة بنظام FAT32 أو exFAT والمحاولة مجدداً."); }
    public static String readNowNotVerified() { return t("Not verified on this firmware, so nothing was read.", "لم يتم التحقق من الفيرموير، لذا تعذرت قراءة الحساس."); }
    public static String sensorNow(long pct, String raw) {
        return t("Sensor now: " + pct + "%   (raw " + raw + ")", "قراءة الحساس الآن: " + pct + "%  (القيمة الخام: " + raw + ")");
    }
    public static String shareDataTitle() { return t("Help improve Battery Watchdog?", "المساعدة في تحسين Battery Watchdog؟"); }
    public static String shareDataMsg() {
        return t("The warnings are simple for now. Logs from real cars, especially ones whose 12V battery later failed, would help build smarter checks for everyone.\n\nThis is completely optional, and nothing is ever sent from your car. If you'd like to help, after a few weeks of driving you can export a sanitized copy of your log to a USB drive and share it.\n\nDates are changed to relative times (\"Day 1, Day 2\") so nobody can see when you drive.",
                 "تساعد سجلات البطارية من السيارات الفعلية في تحسين كفاءة التنبيهات واكتشاف مشاكل البطاريات مبكراً.\n\nالمشاركة اختيارية تماماً، ولا يتم إرسال أي بيانات من سيارتك نهائياً. إذا رغبت بالمساعدة، يمكنك بعد عدة أسابيع تصدير نسخة مجهولة الهوية إلى فلاشة USB ومشاركتها.\n\nيتم إخفاء التواريخ وتحويلها إلى أوقات نسبية لحماية خصوصيتك.");
    }
    public static String overrideTitle() { return t("Use built-in numbers on this firmware?", "استخدام أرقام العمليات المدمجة؟"); }
    public static String overrideMsg(String fw) {
        return t("This firmware (" + fw + ") hasn't been verified, and Battery Watchdog couldn't confirm the transaction numbers by name. On a different firmware the same numbers can reach a different function, including ones that reset or change settings.\n\nOnly continue if you've run the USB probe on this car and its txn 137 / 74 / 214 values look like a 12V %, a main battery %, and a health index.",
                 "هذا الفيرموير (" + fw + ") لم يتم التحقق منه مسبقاً، ولم تتمكن الأداة من تأكيد أرقام العمليات تلقائياً بالاسم. قد تؤدي الأرقام الخاطئة في فيرموير غير متوافق إلى وظائف أخرى قد تغير الإعدادات.\n\nتابع فقط إذا كنت متأكداً من الأرقام وقمت بفحص سيارتك مسبقاً.");
    }
    public static String overrideSaved() { return t("Saved. The monitor starts within a minute.", "تم الحفظ. سيبدأ المراقب بالعمل خلال دقيقة."); }
    public static String reportSaved(String name, String usb, String err) {
        if (err.length() > 0) {
            return t("Couldn't write " + name + " to " + usb + ":\n" + err, "تعذر حفظ " + name + " في " + usb + ":\n" + err);
        }
        return t("Wrote " + name + " to " + usb + ".\n\nIt's a plain text file that opens on any computer or phone.",
                 "تم حفظ التقرير باسم " + name + " على الفلاشة " + usb + ".\n\nالملف نصي ويمكن فتحه بسهولة على أي كمبيوتر أو هاتف.");
    }
    public static String exportComplete(int rows, int drives, int warns, String dir) {
        return t("Wrote " + rows + " readings, " + drives + " drives and " + warns + " warnings to:\n" + dir + "\n\nFiles: readings.csv, drives.csv, warnings.csv, summary.txt, plus raw state files.",
                 "تم تصدير " + rows + " قراءة، و " + drives + " رحلة، و " + warns + " تنبيهاً بنجاح إلى:\n" + dir + "\n\nالملفات: readings.csv، drives.csv، warnings.csv، summary.txt، بالإضافة لملفات الحالة الخام.");
    }
    public static String shareExportComplete(String zip) {
        return t("Created share zip on USB:\n" + zip + "\n\nIt contains relative dates, readings and drives, ready to attach to an issue or share.",
                 "تم إنشاء ملف المشاركة بنجاح على الفلاشة:\n" + zip + "\n\nالملف يحتوي على قراءات وسجلات بتواريخ مجهولة الهوية وجاهزة للمشاركة.");
    }
}
