package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

@SuppressLint("Registered")
public class MyIntentService extends IntentService {

    boolean flag = true;
    int id;

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    public int onStartCommand( Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (flag) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour == 9){
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                Intent intent1 = new Intent(getApplicationContext() , MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext() , 100 , intent1 , PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext() , "ch1");
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.drawable.logo);
                builder.setContentTitle("Shopp App");
                builder.setContentText("Do you want to shopp today ?");
                builder.setAutoCancel(true);
                builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                manager.notify(id , builder.build());
                id++;
            }
        }
    }
}