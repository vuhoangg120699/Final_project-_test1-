package com.dinhvu.quanlysuckhoe.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;


import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.dinhvu.quanlysuckhoe.Model.NuocUong;
import com.dinhvu.quanlysuckhoe.R;
import com.dinhvu.quanlysuckhoe.View.DangNhapActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class Notifications extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    private ArrayList<NuocUong> arrayList;
    private NuocUong nuocUong;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat,simpleDateFormat1;
    private PendingIntent pendingIntent;
    private Date d,d1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Random rd=new Random();
        Intent intent1=new Intent(this, DangNhapActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent=PendingIntent.getActivity(this,0
                ,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        int k=rd.nextInt(999999)+1;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel("ChanelID"+k,"FourGround",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);


        }

        Notification notification =new NotificationCompat.Builder(this,"ChanelID"+k)
                .setContentTitle("Thông Báo")
                .setContentText("Hãy vào uống nước nhanh")
                .setSmallIcon(R.drawable.drinkwater)
                .setContentIntent(pendingIntent).build();

        startForeground(1,notification);

        return START_NOT_STICKY;
    }

    private void CreateNotificatioNChanel() {

    }


}
