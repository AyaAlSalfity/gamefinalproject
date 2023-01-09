package com.example.gamefinalproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.gamefinalproject1.databinding.ActivitySettingBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class SettingActivity extends AppCompatActivity  implements MusicStopListener  {
ActivitySettingBinding binding;
    Animation bt_up, bt_down;
    Animation top , bottom ;
    ViewModel viewModel;
   // MediaPlayer mediaPlayer;
    public static final String Notifction_ID="channel1";
    String audioLink="https://dl.dropboxusercontent.com/s/5ey5xwb7a5ylqps/games_of_thrones.mp3?dl=0";
    boolean musiceplaying=false;
    Intent serviceintent;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    MotionEvent motionEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //mediaPlayer=MediaPlayer.create(SettingActivity.this,R.raw.sound);
        sharedPreference = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        viewModel=new ViewModelProvider(this).get(ViewModel.class);
        // playAudio();
        editor= sharedPreference.edit();
        // set animation
        top = AnimationUtils.loadAnimation(this , R.anim.top);
        bottom = AnimationUtils.loadAnimation(this , R.anim.bottom);

        binding.nonotifictionId.setAnimation(top);
        binding.soundId.setAnimation(bottom);
        binding.raplaygameId.setAnimation(bottom);
        binding.notifictionId.setAnimation(bottom);
        binding.nosoundId.setAnimation(bottom);
        JobScheduler jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        //   createNotification();
        bt_up = AnimationUtils.loadAnimation(this, R.anim.bt_animup);
        bt_down = AnimationUtils.loadAnimation(this, R.anim.bt_down);
        setSupportActionBar(binding.toolBar2);
        setTitle("game");

      //  binding.soundId.setBackgroundResource(R.drawable.sound_removebg_preview);
        ApplictionClass.context=(Context)SettingActivity.this;
        serviceintent=new Intent(this,MusicePlayService.class);
//        binding.soundId.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    binding.soundId.startAnimation(bt_up);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    binding.soundId.startAnimation(bt_down);
//                }
//
//                return true;
//            }
//        });
//        binding.raplaygameId.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    binding.raplaygameId.startAnimation(bt_up);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    binding.raplaygameId.startAnimation(bt_down);
//                }
//
//                return true;
//            }
//        });
//        binding.notifictionId.setOnTouchListener(new View.OnTouchListener() {
//             @Override
//               public boolean onTouch(View view, MotionEvent motionEvent) {
//
//            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//           binding.notifictionId.startAnimation(bt_up);
//         } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//               binding.notifictionId.startAnimation(bt_down);
//             }
//
//               return true;
//             }
//           });
//        binding.nonotifictionId.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    binding.nonotifictionId.startAnimation(bt_up);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    binding.nonotifictionId.startAnimation(bt_down);
//                }
//
//                return true;
//            }
//        });
        binding.soundId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!musiceplaying){
                    playAudio();
                    binding.soundId.setImageResource(R.drawable.cancle_removebg_preview);
                    musiceplaying=true;
                }else {
                    stopplayService();
                    binding.soundId.setImageResource(R.drawable.sound_removebg_preview);
                    musiceplaying=false;
                }
            }
        });

binding.nosoundId.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        MainActivity.mediaPlayer.stop();
    }
});
//binding.soundId.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        MainActivity.mediaPlayer.start();
//    }
//});
//binding.notifictionId.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Toast.makeText(Settings.this, "Remind set", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Settings.this,notifiction.class);
//        PendingIntent pendingIntent=PendingIntent.getBroadcast(Settings.this,0,intent,0);
//        AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
//        long timebuttonclick= System.currentTimeMillis();
//        long timeseconds= 1000 * 10;
//        alarmManager.set(AlarmManager.RTC_WAKEUP,timebuttonclick * timeseconds,pendingIntent);
//    }
//});
        binding.notifictionId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SettingActivity.this, "notifiction on ", Toast.LENGTH_SHORT).show();
                ComponentName componentName=new ComponentName(getBaseContext(),jobservice.class);
                JobInfo info=null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    info=new JobInfo.Builder(101,componentName)
                            .setPeriodic(1440*60*1000,JobInfo.getMinFlexMillis()).build();
               }
                jobScheduler.schedule(info);
            }
        });
        binding.nonotifictionId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "notifiction off", Toast.LENGTH_SHORT).show();
                jobScheduler.cancel(101);
            }
        });
        binding.raplaygameId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LevelTable levelTable=new LevelTable();
                PuzzleTaple puzzleTaple=new PuzzleTaple();
viewModel.deleteLevel(levelTable);
viewModel.deletePuzzle(puzzleTaple);

                        try {
                            JSONArray jsonArray = new JSONArray(readFromAssets());
                            for (int x = 0; x < jsonArray.length(); x++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(x);
                                int level_no = (int) jsonObject.get("level_no");
                            //    editor.putInt("questionsCount" + level_no, 0);
                                editor.putInt("points" + level_no, 0);
                                Toast.makeText(SettingActivity.this, "replay", Toast.LENGTH_SHORT).show();
                            }
                            editor.putInt("totalPoints", 0);
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

       }
    public void playAudio() {
        serviceintent.putExtra("AudioLink",audioLink);
        try {
            startService(serviceintent);
        }catch (SecurityException e){
            Toast.makeText(this, "Eroor"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void stopplayService(){
        try {
            stopService(serviceintent);

        }catch (SecurityException e){
            Toast.makeText(this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void OnMusicStopped() {
        binding.soundId.setImageResource(R.drawable.sound_removebg_preview);
        musiceplaying=false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusettings, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

//    private void createNotification(){
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            CharSequence name="Remind Channel";
//            String description="Channel for remind";
//            int importance=NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel=new NotificationChannel(Notifction_ID,name, importance);
//            channel.setDescription(description);
//            NotificationManager manager=getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//        }
//    }
private String readFromAssets( ) {
    String string = "";
    try {
        InputStream inputStream = getAssets().open("jsonData.json");
        int size = inputStream.available();
        byte[] byteObject = new byte[size];
        inputStream.read(byteObject);
        inputStream.close();
        string = new String(byteObject , "UTF-8");

    } catch (IOException e) {
        e.printStackTrace();
    }
    return string ;

}

}