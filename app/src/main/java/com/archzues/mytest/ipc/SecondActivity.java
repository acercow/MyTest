package com.archzues.mytest.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.archzues.mytest.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        UserManager.sUserId = 2;
        Log.i("ipc", "SecondActivity id : " + UserManager.sUserId);
        startActivity(new Intent(this, ThirdActivity.class));
    }
}
