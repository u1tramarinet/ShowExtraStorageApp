package com.example.takumi.showextrastorageapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class StorageUtil {
    private static final String TAG = StorageUtil.class.getSimpleName();
    private static final int REQUEST_CODE = 114;

    public static boolean requestPermission(Activity activity) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "granted already");
            return true;
        }
        Log.d(TAG, "not granted Let's request");
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE);
        return false;
    }

    public static boolean isRequestPermissionGranted(int requestCode, String[] permissions, int[] requestResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (requestResults.length > 0
                        && requestResults[0] == PackageManager.PERMISSION_GRANTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
