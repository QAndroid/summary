package com.qandroid.summary.memoryleak;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qandroid.summary.R;

public class InnerClassActivity extends AppCompatActivity {
    //使用静态成员，使得资源在此Activity中仅有一份
    private static ResourceClass resourceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innerclass);

        //在该资源在内存中不存在的情况下，在构造获取资源，避免资源内存的重复分配
        if (resourceClass == null) {
            //但是该资源未非静态内部类，它的实例隐式的含有外部类InnerClassActivity的引用，当Activity退出的时候，该Activity无法被回收导致内存泄露
            resourceClass = new ResourceClass();
        }

    }

    //保存资源的内部类
    static class ResourceClass {

    }
}
