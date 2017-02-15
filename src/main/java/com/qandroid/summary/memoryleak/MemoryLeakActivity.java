package com.qandroid.summary.memoryleak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qandroid.summary.R;

public class MemoryLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        MemoryLeakManager memoryLeakManager = MemoryLeakManager.getInstance(this);
    }
}
