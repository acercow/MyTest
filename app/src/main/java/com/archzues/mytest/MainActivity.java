package com.archzues.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Toast;

import com.archzues.mytest.eventdispatch.$Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        startActivity(new Intent(this, HorizontalActivity.class));
//        finish();
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.container).scrollBy(-20, -20);
            }
        });

    }

}
