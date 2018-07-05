package com.archzues.mytest.eventdispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.archzues.mytest.R;

public class TouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        startActivity(new Intent(this, HorizontalActivity.class));
//        finish();
        setContentView(R.layout.activity_touch);
        $Log.tvText = findViewById(R.id.text_log);
        findViewById(R.id.clear).setOnClickListener(v -> $Log.tvText.setText(""));

    }

}
