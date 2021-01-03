package com.example.shoppingapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int second = calendar.get(Calendar.SECOND);

        if (hour == 9 && second == 1) {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "ch1");
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.logo);
            builder.setContentTitle("Shopp App");
            builder.setContentText("Do you want to shopp today ?");
            builder.setAutoCancel(true);
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            manager.notify(1, builder.build());

        }
        return super.onStartCommand(intent, flags, startId);
    }
}

