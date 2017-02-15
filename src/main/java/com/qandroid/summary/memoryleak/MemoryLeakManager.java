package com.qandroid.summary.memoryleak;

import android.content.Context;

/**
 * Created by chengxiang.peng on 2017/2/15.
 */

public class MemoryLeakManager {
    private static MemoryLeakManager instance;
    private Context context;

    private MemoryLeakManager(Context context) {
        this.context = context;
    }

    public static MemoryLeakManager getInstance(Context context) {
        if (instance == null) {
            instance = new MemoryLeakManager(context);
        }
        return instance;
    }
}
