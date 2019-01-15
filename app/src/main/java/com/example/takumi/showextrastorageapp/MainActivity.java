package com.example.takumi.showextrastorageapp;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        initComponents();
        if(StorageUtil.requestPermission(this)) {
            displaySdCardFilesDirPath();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (StorageUtil.isRequestPermissionGranted(requestCode, permissions, grantResults)) {
            displaySdCardFilesDirPath();
        }
    }

    private void displaySdCardFilesDirPath() {
        File[] dirArr = getBaseContext().getExternalFilesDirs(null);
        Log.d(TAG, "path count=" + dirArr.length);

        for (File dir : dirArr) {
            if (dir != null) {
                String path = dir.getAbsolutePath();

                if (Environment.isExternalStorageRemovable(dir)) {
                    path += "[REMOVABLE]";
                }
                mTextView.append(path + "\n");
            }
        }
    }

    private void initComponents() {
        mTextView = findViewById(R.id.textView);
    }
}
