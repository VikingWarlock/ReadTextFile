package com.example.viking.readtextfile;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vkwk on 10/14/16.
 * SDCard Helper
 */
public class SDHelper {
    static public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    static public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    static public String storagePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }


    static public Boolean isFileDirectory(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    static public List<String> getFileList(String path) {
        if (!isFileDirectory(path)) return null;
        List<String> list = new ArrayList<>();
        try {
            File sdPath = new File(path);
            if (sdPath.listFiles().length > 0) {
                for (File file : sdPath.listFiles()) {
                    list.add(file.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            //
        }
        return list;
    }
}
