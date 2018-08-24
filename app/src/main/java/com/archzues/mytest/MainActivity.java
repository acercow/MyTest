package com.archzues.mytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.archzues.mytest.ipc.User;
import com.archzues.mytest.ipc.normal.BookMangerActivity;

public class MainActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(MainActivity.this, "ni da ye yeyeyeyeyeyeyeye", Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter("zhaosen.vip"));
        sendBroadcast(new Intent("zhaosen.vip"));
//        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("zhaosen.vip"));
        try {
            user.getUserName();
            int i = 8 / 0;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        startActivity(new Intent(this, BookMangerActivity.class));



        finish();


        findViewById(R.id.btn_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.container).scrollBy(-20, -20);
            }
        });

    }

}
