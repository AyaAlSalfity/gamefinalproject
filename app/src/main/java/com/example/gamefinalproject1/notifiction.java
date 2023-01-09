package com.example.gamefinalproject1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class notifiction extends BroadcastReceiver {
    public static final String Notifction_ID="channel1";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,Notifction_ID);
        builder.setSmallIcon(R.drawable.notification);
        builder.setContentTitle("Remind to play a game");
        builder.setContentText("The puzzle is waiting for you");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managercompat= NotificationManagerCompat.from(context);
        managercompat.notify(200,builder.build());
    }
}
