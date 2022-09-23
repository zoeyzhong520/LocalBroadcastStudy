package com.example.localbroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

// 本地广播的实现，调用了LocalBroadcastManager
// 本地广播仅在本应用程序内传播，外部其他的应用程序接收不到该广播发出的信号
public class MainActivity extends AppCompatActivity {
    private static final String BROADCAST_ACTION = "com.example.localbroadcast.MY_LOCAL";

    // intentFilter 意图过滤器，设置自身需要监听的广播信号
    private IntentFilter intentFilter;

    // localBroadcastManager 本地广播管理
    private LocalBroadcastManager localBroadcastManager;

    // localReceiver 本地广播接收器
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取一个本地广播管理的对象
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        // 设置自身需要监听的广播信号
        intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);

        // 注册本地广播监听器
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    // 本地广播的发送者
    public void localBroadcast(View view) {
        Intent intent = new Intent(BROADCAST_ACTION);
        localBroadcastManager.sendBroadcast(intent);
    }

    // LocalReceiver 内部类，实现了onReceive方法
    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "收到了本地发出的广播", Toast.LENGTH_SHORT).show();
        }
    }
}

