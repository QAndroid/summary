package com.qandroid.summary.memoryleak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qandroid.summary.R;

public class SingletonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
        //将SingletonActivity上下文，传递给单例成员持有，在Activity退出时无法被回收
        SingletonManager singletonManager = SingletonManager.getInstance(this);
    }
}
