package com.moveosoftware.infrastructure.permissions;

/**
 * Created by Zari on 5/30/2016
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PermissionsManager {

    private static HashMap<Integer, Runnable> Callbacks = new HashMap<>();

    public static boolean isPermissionsGranted(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyPermissions(Activity activity, String... permissions) {
        return verifyPermissions(activity, null, permissions);
    }

    public static boolean verifyPermissions(Activity activity, Runnable runnable, String... permissions) {
        List<String> permissionsToRequest = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                if (permission.equals(Manifest.permission.WRITE_SETTINGS)) {
                    if (!verifyWriteSettingsPermissions(activity)) {
                        return false;
                    }
                    continue;
                }
                permissionsToRequest.add(permission);
            }
        }

        if (permissionsToRequest.isEmpty()) {
            if (runnable != null) {
                runnable.run();
                ActivityCompat.requestPermissions(activity, permissions, 0);
            }
            return true;
        }

        Integer requestCode;
        do {
            requestCode = new Random(System.currentTimeMillis()).nextInt(Byte.MAX_VALUE);
        } while (Callbacks.containsKey(requestCode));
        Callbacks.put(requestCode, runnable);

        String[] arr = permissionsToRequest.toArray(new String[permissionsToRequest.size()]);
        ActivityCompat.requestPermissions(activity, arr, requestCode);
        return false;
    }

    public static boolean verifyWriteSettingsPermissions(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (Settings.System.canWrite(activity)) {
            return true;
        }

        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
        return false;
    }

    public static void runCallback(int requestCode) {
        Runnable callback = Callbacks.remove(requestCode);
        if (callback != null) {
            callback.run();
        }
    }
}