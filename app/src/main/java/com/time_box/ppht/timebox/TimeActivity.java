package com.time_box.ppht.timebox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        //设置工具栏
        Toolbar myToolbar = (Toolbar) findViewById(R.id.time_toolbar);
        setSupportActionBar(myToolbar);
        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
