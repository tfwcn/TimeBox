package com.time_box.ppht.timebox.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.time_box.ppht.timebox.MainActivity;
import com.time_box.ppht.timebox.Service.NotificationAudioService;

/**
 * Created by YC on 2017/5/7.
 */

public class HoldBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            //打开通知栏服务
            Intent startServiceIntent = new Intent(context, NotificationAudioService.class);
            context.startService(startServiceIntent);
    }
}
