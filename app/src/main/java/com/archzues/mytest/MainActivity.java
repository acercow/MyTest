package com.archzues.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Toast;

import com.archzues.mytest.eventdispatch.$Log;
import com.archzues.mytest.eventdispatch.HorizontalActivity;
import com.archzues.mytest.eventdispatch.TouchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, TouchActivity.class));
        finish();
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.container).scrollBy(-20, -20);
            }
        });

    }

}
