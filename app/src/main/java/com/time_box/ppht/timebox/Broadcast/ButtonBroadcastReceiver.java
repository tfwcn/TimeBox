package com.time_box.ppht.timebox.Broadcast;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import java.lang.reflect.Method;

/**
 * Created by YC on 2017/5/7.
 * 通知栏按钮广播接收器
 */

public class ButtonBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AudioManager audioManage = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        Object statusBarManager = context.getSystemService("statusbar");

        String action = intent.getAction();
        if (action.equals(com.time_box.ppht.timebox.Service.NotificationAudioService.notificationBroadcastID)) {
            //通过传递过来的ID判断按钮点击属性或者通过getResultCode()获得相应点击事件
            int buttonId = intent.getIntExtra("id", 0);
            switch (buttonId) {
                case 1:
                    //收回任务栏
                    if (statusBarManager != null) {
                        try {
                            Class<?> clazz = Class.forName("android.app.StatusBarManager");
                            int sdkVersion = android.os.Build.VERSION.SDK_INT;
                            Method collapse = null;
                            if (sdkVersion <= 16) {
                                collapse = clazz.getMethod("collapse");
                            } else {
                                collapse = clazz.getMethod("collapsePanels");
                            }
                            collapse.setAccessible(true);
                            collapse.invoke(statusBarManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //显示音量调节界面
                    audioManage.adjustVolume(AudioManager.ADJUST_SAME, AudioManager.FLAG_SHOW_UI);
                    break;
                default:
                    break;
            }
        }
    }
}
