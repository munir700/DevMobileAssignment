package assignment.cleancode.mobiledevassignment.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class Utils {
    public static String getAppVersionName(Context context) {
        try {
            return context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
