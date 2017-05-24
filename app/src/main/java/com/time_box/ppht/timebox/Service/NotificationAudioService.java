package com.time_box.ppht.timebox.Service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;

import com.time_box.ppht.timebox.Broadcast.ButtonBroadcastReceiver;
import com.time_box.ppht.timebox.MainActivity;
import com.time_box.ppht.timebox.R;
import com.time_box.ppht.timebox.ResourcesHelper;

public class NotificationAudioService extends Service {
    //通知栏ID
    private static final int notificationID = 535;
    //广播ID，用于接收到广播时进行判断
    public static final String notificationBroadcastID = "com.time_box.ppht.timebox.notification";
    private NotificationManager notificationManager;
    private Notification notification;
    private AlarmManager alarmManager;
    private ResourcesHelper resourcesHelper;
    private ButtonBroadcastReceiver receiver;//广播接收器

    public class MyBinder extends Binder {
        public Context ContextMainActivity;
    }

    public NotificationAudioService() {
    }

    @Override
    public void onCreate() {
        resourcesHelper = new ResourcesHelper(this);
        notificationManager = (NotificationManager)
                getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        //获取AlarmManager系统服务
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        receiver = new ButtonBroadcastReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AudioService", "onStartCommand");
        showNotification();
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;//被kill后重启
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d("AudioService", "onDestroy");
        notification.deleteIntent = null;
        //关闭通知
        notificationManager.cancel(notificationID);
        super.onDestroy();
    }

    private void showNotification() {
        //删除通知后重新调用服务恢复通知显示
        Intent notificationIntent = new Intent(NotificationAudioService.this, MainActivity.class); // 点击该通知后要跳转的Activity
        PendingIntent contentItent = PendingIntent.getActivity(NotificationAudioService.this, 0,
                notificationIntent, 0);
        Intent notificationIntent2 = new Intent(NotificationAudioService.this, NotificationAudioService.class); // 点击该通知后要跳转的Activity
        PendingIntent deleteItent = PendingIntent.getService(NotificationAudioService.this, 0,
                notificationIntent2, 0);

        //通知栏视图
        RemoteViews rv = new RemoteViews(NotificationAudioService.this.getPackageName(), R.layout.notification_audio);
        rv.setTextViewText(R.id.notification_tvTitle, "震动模式");
        String notificationvContent = "离下一任务还有"
                + "<font color = '" + resourcesHelper.GetColorString(R.color.colorContentHot) + "'>1</font>"
                + "<font color = '" + resourcesHelper.GetColorString(R.color.colorContentFocus) + "'>小时</font>"
                + "<font color = '" + resourcesHelper.GetColorString(R.color.colorContentHot) + "'>29</font>"
                + "<font color = '" + resourcesHelper.GetColorString(R.color.colorContentFocus) + "'>分</font>"
                + "<font color = '" + resourcesHelper.GetColorString(R.color.colorContentHot) + "'>23</font>"
                + "<font color = '" + resourcesHelper.GetColorString(R.color.colorContentFocus) + "'>秒</font>"
                + "";
        rv.setTextViewText(R.id.notification_tvContent, Html.fromHtml(notificationvContent, Html.FROM_HTML_MODE_LEGACY));
        //按钮事件
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(notificationBroadcastID);//注册要监听的广播
        registerReceiver(receiver, intentFilter);
        Intent intent = new Intent(notificationBroadcastID);//触发的广播
        intent.putExtra("id", 1);
        PendingIntent intentpi = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setOnClickPendingIntent(R.id.notification_btnAudio, intentpi);

        // 定义Notification的各种属性
        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.timebox)
                .setContentIntent(contentItent)
                .setDeleteIntent(deleteItent)
                .setCustomContentView(rv)
//                .setLargeIcon(R.drawable.timebox)
                ;
        notification = notificationBuilder.build();
        Log.i("getPackageName", NotificationAudioService.this.getPackageName());
        notification.flags = Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
        notification.flags |= Notification.FLAG_NO_CLEAR; // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用

//        notification.flags |= Notification.FLAG_SHOW_LIGHTS;//打开指示灯
//        notification.defaults = Notification.DEFAULT_LIGHTS;
//        notification.ledARGB = Color.BLUE;
//        notification.ledOnMS =5000;

        // 把Notification传递给NotificationManager
        notificationManager.notify(notificationID, notification);
    }

    //暂时无用
    private void reStartService(){

        //包装需要执行Service的Intent
        Intent intent = new Intent(this, NotificationAudioService.class);
        intent.setAction(notificationBroadcastID);
        intent.setPackage(getPackageName());
//        if (android.os.Build.VERSION.SDK_INT >= 12){
//            intent.setFlags(32);
//        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);

        //触发服务的起始时间
        long triggerAtTime = SystemClock.elapsedRealtime();

        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime,30 * 1000, pendingIntent);
    }
}
