package com.qandroid.summary.memoryleak;

import android.content.Context;

/**
 * Created by chengxiang.peng on 2017/2/15.
 */
public class SingletonManager {
    //单例的静态成员生命周期和应用一样长
    private static SingletonManager instance;
    //它持有的成员，Activity的上下文在Activity关闭的时候无法回收，故造成了内存泄露
    private Context context;

    private SingletonManager(Context context) {
        this.context = context;
    }

    public static SingletonManager getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonManager(context);
        }
        return instance;
    }
}
