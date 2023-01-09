package com.example.gamefinalproject1;

import android.app.job.JobParameters;
import android.app.job.JobService;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class jobservice extends JobService {
    public static final String Notifction_ID="channel1";
    //  MediaPlayer mediaPlayer;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        // if (mediaPlayer==null){
        ///   mediaPlayer=MediaPlayer.create(jobservice.this,R.raw.sound);
        // }
        //  mediaPlayer.start();
        NotificationCompat.Builder builder=new NotificationCompat.Builder(jobservice.this,Notifction_ID);
        builder.setSmallIcon(R.drawable.notification);
        builder.setContentTitle("Remind to play a game");
        builder.setContentText("The puzzle is waiting for you");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat managercompat= NotificationManagerCompat.from(jobservice.this);
        managercompat.notify(200,builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
