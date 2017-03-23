package com.nick.bb.fitness.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by sharpay on 17-3-22.
 */

public class FileUtil {

    private static final String HTTP_CACHE_DIR = "http";

    public static File getHttpCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(context.getExternalCacheDir(), HTTP_CACHE_DIR);
        }
        return new File(context.getCacheDir(), HTTP_CACHE_DIR);
    }

}
