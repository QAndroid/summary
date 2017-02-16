package com.qandroid.summary.memoryleak;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qandroid.summary.R;

public class HandlerActivity extends AppCompatActivity {
    private TextView textView;

    //使用非静态匿名内部类实例创建Handler对象，持有HandlerActivity的隐式引用
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                Log.i("HandlerActivity","receive sendMessage");
                textView.setText((CharSequence) msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView = (TextView) findViewById(R.id.textView1);

        Log.i("HandlerActivity","start loaddata");
        new LoadDataTread().start();
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
            handler.sendMessageDelayed(message,10000);
        }
    }
}
