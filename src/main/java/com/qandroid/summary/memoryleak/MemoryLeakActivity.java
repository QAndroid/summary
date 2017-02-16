package com.qandroid.summary.memoryleak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qandroid.common.QBaseActivity;
import com.qandroid.summary.NameSpaceActivity;
import com.qandroid.summary.R;

public class MemoryLeakActivity extends QBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoryleak);
    }

    public void toSingleton(View view) {
        toActivityByClass(SingletonActivity.class);
    }

    public void toInnerClass(View view) {
        toActivityByClass(InnerClassActivity.class);
    }

    public void toThreadAsync(View view) {
        toActivityByClass(ThreadAsyncActivity.class);
    }
}
