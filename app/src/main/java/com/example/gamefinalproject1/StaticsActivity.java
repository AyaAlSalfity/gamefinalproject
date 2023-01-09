package com.example.gamefinalproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.job.JobScheduler;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.gamefinalproject1.databinding.ActivityStaticsBinding;

public class StaticsActivity extends AppCompatActivity implements Fragment {
ActivityStaticsBinding binding;
    Animation bt_up, bt_down;
    Animation top , bottom ;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    public static final String falseanswer_key="false";
    public static final String trueanswer_key="true";
    public static final String question_key="question";
      ViewModel viewModel;
    int correct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStaticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        int correct=getIntent().getIntExtra("correct",0);
//        int wrong=getIntent().getIntExtra("wrong",0);
//        binding.txtTrue.setText(correct);
//        binding.txtFalse.setText(wrong);
        top = AnimationUtils.loadAnimation(this , R.anim.top);
        bottom = AnimationUtils.loadAnimation(this , R.anim.bottom);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        binding.logo.setAnimation(top);
        binding.txtFalse.setAnimation(bottom);
        binding.txtTrue.setAnimation(bottom);
        binding.txt.setAnimation(bottom);
        binding.txt2.setAnimation(bottom);
        binding.question.setAnimation(bottom);
        binding.numberOfquestionend.setAnimation(bottom);
        bt_up = AnimationUtils.loadAnimation(this, R.anim.bt_animup);
        bt_down = AnimationUtils.loadAnimation(this, R.anim.bt_down);
        sharedPreference = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        editor= sharedPreference.edit();
        String f = sharedPreference.getString(falseanswer_key, null);
        String t = sharedPreference.getString(trueanswer_key, null);
        String q = sharedPreference.getString(question_key, null);

        editor.apply();
        binding.txtFalse.setText(f);
        binding.txtTrue.setText(t);
          binding.question.setText(q);
          PuzzleTaple puzzleTaple=new PuzzleTaple();
          LevelTable levelTable=new LevelTable();
//if (puzzleTaple==null){
//    editor.clear();
//}

    }

    @Override
    public void Intent(int correct) {
        binding.txtTrue.setText(correct);
    }

    @Override
    public void Intent2(int wrong) {
        binding.txtFalse.setText(wrong);
    }
}