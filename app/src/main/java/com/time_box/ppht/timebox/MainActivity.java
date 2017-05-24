package com.time_box.ppht.timebox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.time_box.ppht.timebox.Adapter.TaskAdapter;
import com.time_box.ppht.timebox.Model.TaskInfo;
import com.time_box.ppht.timebox.Service.NotificationAudioService;

public class MainActivity extends AppCompatActivity {
    private ListView mTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        //列表
        mTaskList = (ListView) findViewById(R.id.main_taskList);
        TaskAdapter taskAdapter = new TaskAdapter(this, R.layout.main_list_item);
        mTaskList.setAdapter(taskAdapter);
        for (int i = 0; i < 30; i++) {
            TaskInfo ti = new TaskInfo();
            ti.name = "模式" + i;
            taskAdapter.add(ti);
        }
        //打开通知栏服务
        Intent startServiceIntent = new Intent(MainActivity.this, NotificationAudioService.class);
        startService(startServiceIntent);
    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 菜单选项事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_setting:
                //设置
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_about:
                //关于
                Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //新增定时器
    public void addTask(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//        AudioManager audioMgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        if (audioMgr.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
//            //静音
//            audioMgr.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
//        } else {
//            //正常
//            audioMgr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//        }
//        Intent intent = new Intent(MainActivity.this, TimeActivity.class);
//        startActivity(intent);
//        Intent startServiceIntent = new Intent(MainActivity.this, NotificationAudioService.class);
//        stopService(startServiceIntent);//关闭通知
        //跳转到任务设置界面
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        startActivity(intent);
    }
}
