package com.qandroid.summary.memoryleak;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qandroid.summary.R;

public class ThreadAsyncActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadasync);

        //匿名内部类实例，包含ThreadAsyncActivity隐式的引用
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //模拟异步任务执行，在ThreadAsyncActivity退出后还会继续执行，导致Activity无法被回收
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //非静态内部类实例对象，包含ThreadAsyncActivity隐式的引用
        new InnerAsyncTask().execute();
    }

    //非静态内部类实现异步任务
    class InnerAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //模拟异步任务执行，在ThreadAsyncActivity退出后还会继续执行，导致Activity无法被回收
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
