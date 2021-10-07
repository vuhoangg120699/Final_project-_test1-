package com.dinhvu.quanlysuckhoe.Services;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Build;
        import android.util.Log;

public class AlamServer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent(context, Notifications.class);
        Log.e("TIMER","HERRE");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent1);
        }else{
            context.startService(intent1);
        }

    }

}
