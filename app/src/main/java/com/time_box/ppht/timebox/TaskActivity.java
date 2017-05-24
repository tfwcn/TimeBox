package com.time_box.ppht.timebox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        //设置工具栏
        Toolbar myToolbar = (Toolbar) findViewById(R.id.task_toolbar);
        setSupportActionBar(myToolbar);
        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("onOptionsItemSelected",String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case R.id.task_menu_finsh:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void selectTime(View view){
        //跳转到时间设置界面
        Intent intent = new Intent(TaskActivity.this, TimeActivity.class);
        startActivity(intent);
    }
}
