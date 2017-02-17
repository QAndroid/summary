package com.qandroid.summary.memoryleak;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qandroid.summary.R;

public class ThreadAsyncActivity extends AppCompatActivity {
    private Thread thread;
    private InnerAsyncTask innerAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadasync);

        thread = new Thread(new MyRunnable());
        thread.start();
        innerAsyncTask = new InnerAsyncTask();
        innerAsyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        //在Activity销毁的时候，退出线程执行和异步任务的执行，避免后台继续执行消耗资源
        if(null != thread){
            try {
                thread.interrupt();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(null != innerAsyncTask){
            innerAsyncTask.cancel(true);
        }

        super.onDestroy();
    }

    //使用静态内部类，避免对外部类ThreadAsyncActivity实例的引用
    static class InnerAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    //使用静态内部类，避免对外部类ThreadAsyncActivity实例的引用
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
