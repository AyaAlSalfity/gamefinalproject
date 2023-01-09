package com.example.gamefinalproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.gamefinalproject1.databinding.ActivityHomeBinding;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
ActivityHomeBinding binding;
Animation top , bottom ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // set animation
        top = AnimationUtils.loadAnimation(this , R.anim.top);
        bottom = AnimationUtils.loadAnimation(this , R.anim.bottom);

        binding.settingId.setAnimation(bottom);
        binding.startGameId.setAnimation(bottom);
        binding.logoutId.setAnimation(bottom);
        setSupportActionBar(binding.toolBar);
        setTitle("game");
        binding.settingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.logoutId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();

            }
        });
        ViewModel vm = new ViewModelProvider(this).get(ViewModel.class);
        vm.getAllQuestions().observe(this, new Observer<List<PuzzleTaple>>() {
            @Override
            public void onChanged(List<PuzzleTaple> Data) {
                for (PuzzleTaple q :
                        Data) {
                    Log.d("questionsTest", "onChanged: "+q.getId()+" : "+q.getTitle()+" : "+q.getLevelNo());

                }
            }
        });
binding.startGameId.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
Intent intent=new Intent(getBaseContext(),StartgameActivity.class);
startActivity(intent);
//finish();
    }
});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_profile:

                Intent intent=new Intent(getBaseContext(),ProfileActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_setting:

                Intent intent2=new Intent(getBaseContext(),SettingActivity.class);
               startActivity(intent2);
                return true;
        }

        return false;
    }
    public void logout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Are you sure you want to exit the application?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}