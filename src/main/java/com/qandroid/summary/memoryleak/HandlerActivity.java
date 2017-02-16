package com.qandroid.summary.memoryleak;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qandroid.summary.R;

import java.lang.ref.WeakReference;

public class HandlerActivity extends AppCompatActivity {
    private TextView textView;
    private MyHander myHander = new MyHander(this);

    //创建静态的内部类，不会有对HandlerActivity实例的隐式引用，防止Activity退出后，消息为处理完Activity无法回收
    private static class MyHander extends Handler{
        //使用弱引用持有上下文，这样即使消息未处理完也可以回收Activity,避免内存泄露
        private WeakReference<Context> contextWeakReference;

        public MyHander(Context context){
            contextWeakReference = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                Log.i("HandlerActivity","receive sendMessage");
                HandlerActivity activity = (HandlerActivity) contextWeakReference.get();
                if(activity != null){
                    activity.textView.setText((CharSequence) msg.obj);
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView = (TextView) findViewById(R.id.textView1);

        Log.i("HandlerActivity","start loaddata");
        new LoadDataTread().start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //在Activity退出页面将要被销毁的时候哦，移除消息队列的所有消息和Runnable,防止内存泄露
        myHander.removeCallbacksAndMessages(null);
    }

    class LoadDataTread extends Thread{
        @Override
        public void run() {
            try {
                //延迟1秒钟，模拟异步加载数据
                Thread.sleep(1000);
                Log.i("HandlerActivity","done loaddata");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //处理完网络请求，延迟10秒发送消息，并退出HandlerActivity页面，此时消息队列还有消息没有处
            //理或正在处理，而消息队列的message持有handler对象的引用，hander又持有Activity的引用，故造成了Activity无法被回收的内存泄露
            Log.i("HandlerActivity","start sendMessage");
            Message message = new Message();
            message.what = 1;
            message.obj = "receive data";
            myHander.sendMessageDelayed(message,10000);
        }
    }
}
