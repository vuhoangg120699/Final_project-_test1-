package com.dinhvu.quanlysuckhoe.Services;

import android.app.*;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.dinhvu.quanlysuckhoe.R;
import com.dinhvu.quanlysuckhoe.View.DangNhapActivity;


public class NotificationTime extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    CreateNotificatioNChanel();
        Intent intent1=new Intent(this, DangNhapActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0
                ,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
     Notification notification =new NotificationCompat.Builder(this,"ChanelID")
                .setContentTitle("Thông Báo")
                .setContentText("Hãy vào ứng dụng thực hiện nhiệm vụ bạn ơi !")
                .setSmallIcon(R.drawable.drinkwater)
                .setContentIntent(pendingIntent).build();


startForeground(1,notification);

        return START_NOT_STICKY;
    }

    private void CreateNotificatioNChanel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel("ChanelID","FourGround",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
