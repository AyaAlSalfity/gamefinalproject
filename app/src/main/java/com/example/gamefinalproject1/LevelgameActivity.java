package com.example.gamefinalproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.gamefinalproject1.databinding.ActivityLevelgameBinding;

import java.util.ArrayList;
import java.util.List;

public class LevelgameActivity extends AppCompatActivity implements com.example.gamefinalproject1.Fragment {
ActivityLevelgameBinding binding;
    ViewModel module ;
    String title ;
    String answer1 ;
    String answer2 ;
    String answer3 ;
    String answer4 ;
    String hint ;
    String correctAnswer ;
    int point;
    int points=0;
    int levelid;
    int puzzleid;
    int durtion;
    int time;
    int trueanswer=0;
    int question=0;
    public static final String falseanswer_key="false";
    public static final String trueanswer_key="true";
    public static final String question_key="question";


    int falseanser=0;
    int truefalse;
    int id;
    Animation bt_up, bt_down;
    Animation top , bottom ;
//ViewPager2 viewPager;
    int patternId ;
    int levelNo ;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    ArrayList<Fragment> fragmentArrayList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLevelgameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBar);
        setTitle("game");
        sharedPreference = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        editor= sharedPreference.edit();
        top = AnimationUtils.loadAnimation(getBaseContext() , R.anim.top);
        bottom = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bottom);


        bt_up = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bt_animup);
        bt_down = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bt_down);
       // viewPager=(ViewPager) findViewById(R.id.vp);
      //  viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

       // ViewPager2.isUserInputEnabled = false;
//        binding.viewpager.beginFakeDrag();
//        binding.viewpager.fakeDragBy(-10f);
//        binding.viewpager.endFakeDrag();
        binding.viewpager.setUserInputEnabled(false);
        binding.durtion.setAnimation(top);
        binding.numberlevel.setAnimation(top);
        binding.numberpoint.setAnimation(top);
        binding.numberpuzzel.setAnimation(top);
        fragmentArrayList  = new ArrayList<>();
        Intent intent = getIntent();
        levelNo = intent.getIntExtra("levelNo" , 0);
        module = new ViewModelProvider(this).get(ViewModel.class);
        module.getAllLevel().observe(this, new Observer<List<LevelTable>>() {
            @Override
            public void onChanged(List<LevelTable> Levels) {
                module.getAllQuestionsByLevelId(levelNo).observe(LevelgameActivity.this, new Observer<List<PuzzleTaple>>() {
                    @Override
                    public void onChanged(List<PuzzleTaple> puzzle) {
                        for (int i = 0 ; i < puzzle.size(); i++) {
                            title = puzzle.get(i).getTitle();
                            answer1 = puzzle.get(i).getAnswer1();
                            answer2 = puzzle.get(i).getAnswer2();
                            answer3 = puzzle.get(i).getAnswer3();
                            answer4 = puzzle.get(i).getAnswer4();
                            //id=puzzle.get(i).getId();
                            patternId = puzzle.get(i).getPatternId();
                             hint=puzzle.get(i).getHint();
                             correctAnswer=puzzle.get(i).getTrueAnswer();
                              point=puzzle.get(i).getPoints();
                              levelid=puzzle.get(i).getLevelNo();
                              puzzleid=puzzle.get(i).getCurrentquestion();
                            durtion=  puzzle.get(i).getDuration();

                           //  truefalse=  puzzle.get(i).getPoints();
                           // editor.putInt("0",point);
                           // editor.apply();
                            if(patternId == 1){
                                fragmentArrayList.add(TrueOrFaulseFragment.newInstance(title , patternId,hint,correctAnswer,point,puzzleid,levelid,durtion));
                                editor.putInt("timer",puzzle.get(i).getDuration());
                                editor.commit();
                                timer();

                              //  puzzleid++;
                                  //    binding.numberpuzzel.setText("number puzzle:"+ puzzleid);

//       binding.numberpoint.setText("number point:"+ point);
//        binding.numberlevel.setText("number level:"+ levelNo);
//        binding.numberpuzzel.setText("number puzzle:"+ puzzleid);
      //binding.durtion.setText("durtion:"+ durtion);
                            }
                            else if(patternId == 2){
                                fragmentArrayList.add(ChooseFragment.newInstance(title , answer1 , answer2 , answer3 , answer4 , patternId,hint,correctAnswer,point,puzzleid,levelid,durtion));
                                //puzzleid++;
                                // binding.numberpuzzel.setText("number puzzle:"+ puzzleid);
                             //   binding.durtion.setText("durtion:"+ durtion);
                                editor.putInt("timer",puzzle.get(i).getDuration());
                                editor.commit();
                                timer();
                            }
                            else if(patternId == 3){
                                fragmentArrayList.add(CompleteFragment.newInstance(title , patternId,hint,correctAnswer,point,puzzleid,levelid,durtion));
                             //   puzzleid++;
                           //     binding.numberpuzzel.setText("number puzzle:"+ puzzleid);
                                editor.putInt("timer",puzzle.get(i).getDuration());
                                editor.commit();
                                timer();
                            }
                            AdapterLevel adapter = new AdapterLevel(LevelgameActivity.this , fragmentArrayList);
                            binding.viewpager.setAdapter(adapter);
                        //    binding.durtion.setText("durtion:"+ durtion);
                        }
                    }
                });
            }
        });
        binding.numberlevel.setText("number level:"+ levelNo);
       binding.durtion.setText("durtion:"+ durtion);
        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1);
               points=points-3;
               falseanser++;
                editor.putString(falseanswer_key, String.valueOf(falseanser));
             editor.apply();
//               editor.putInt("falseanswer",falseanser);
//               editor.apply();
                Toast.makeText(LevelgameActivity.this, "f"+falseanser, Toast.LENGTH_SHORT).show();
                binding.numberpoint.setText("number point:"+ points);
                puzzleid=puzzleid+1;
              binding.numberpuzzel.setText("number puzzle"+puzzleid);
            }
        });
      //  if (binding.viewpager.getCurrentItem().)

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusettings,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                Intent intent=new Intent(getBaseContext(),HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
    @Override
    public void Intent(int correct) {
        points=points+point;
        binding.numberpoint.setText("number point:"+ points);
        puzzleid=puzzleid+1;
        trueanswer++;
        question++;
        editor.putString(trueanswer_key, String.valueOf(trueanswer));
        editor.putString(question_key, String.valueOf(question));
        editor.apply();
        Toast.makeText(this, "t"+trueanswer, Toast.LENGTH_SHORT).show();
        binding.numberpuzzel.setText("number puzzle:"+ puzzleid);
        binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1);

    }

    @Override
    public void Intent2(int wrong) {
        points=points;
        binding.numberpoint.setText("number point:"+ points);
        puzzleid=puzzleid+1;
        binding.numberpuzzel.setText("number puzzle:"+ puzzleid);
        falseanser++;
        question++;
        editor.putString(falseanswer_key, String.valueOf(falseanser));
        editor.putString(question_key, String.valueOf(question));
        editor.apply();
        Toast.makeText(this, "f"+falseanser, Toast.LENGTH_SHORT).show();
        binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1);

       // binding.viewpager.getOffscreenPageLimit();
    }
    public  void timer(){
        time=sharedPreference.getInt("timer",0);
        new CountDownTimer(durtion,100){

            @Override
            public void onTick(long l) {
                time++;
                binding.durtion.setText(String.valueOf(time));
            }
            @Override
            public void onFinish() {
           binding.durtion.setText("TIMER END");
             //   binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1);

            }
        }.start();
    }
}