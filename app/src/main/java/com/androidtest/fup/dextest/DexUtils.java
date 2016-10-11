package com.androidtest.fup.dextest;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fup on 2016/10/11.
 */
public class DexUtils {
    public static File copyAssert2Cache(Context context, String path, File dexOut) {
        InputStream inputStream;
        FileOutputStream outputStream;
        try {
            inputStream = context.getAssets().open(path);
            checkDir(dexOut);
            outputStream = new FileOutputStream(dexOut);
            byte[] buf = new byte[4096];
            int l;
            while((l = inputStream.read(buf)) != -1) {
                outputStream.write(buf,0,l);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dexOut;
    }

    public static void checkDir(File file) {
        String path = file.getAbsolutePath();
        if(path == null || path.length() <= 0) {
            return;
        }
        String dirStr = path.substring(0, path.lastIndexOf('/'));
        mkdir(dirStr);
    }

    public static void mkdir(String dirPath) {
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdir();
        }
    }

    public static File getDexPath(Context context, String path) {
        return new File(context.getFilesDir(), path);
    }
}
