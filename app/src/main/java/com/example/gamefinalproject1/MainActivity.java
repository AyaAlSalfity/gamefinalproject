package com.example.gamefinalproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.gamefinalproject1.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Animation splash;
    Animation app_name;
    ViewModel viewModel;
    Animation top , bottom ;
    public  static MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
          if (mediaPlayer==null){
           mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.sound);

        }
         mediaPlayer.start();

        splash= AnimationUtils.loadAnimation(this,R.anim.splash);
        app_name=AnimationUtils.loadAnimation(this,R.anim.nameapp);
        binding.logo.setAnimation(splash);
        binding.nameApp.setAnimation(app_name);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                 Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                   startActivity(intent);
                   finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        String fileName = readFromAssets("jsonData.json");
        parseTheJson(fileName);
    }
    private void parseTheJson(String string) {

        try {

            JSONArray jsonArray = new JSONArray(string);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                int levelNo = jsonObject.getInt("level_no");
                int unlockPoints = jsonObject.getInt("unlock_points");

                LevelTable Level = new LevelTable();
                Level.setLevelNo(levelNo);
                Level.setUnlockPoints(unlockPoints);

                viewModel.insertLevelData(Level);

                JSONArray questionsArray = jsonObject.getJSONArray("questions");

                for (int j = 0; j < questionsArray.length(); j++) {
                    JSONObject jsonObject1 = new JSONObject(questionsArray.get(j).toString());
                    int id = jsonObject1.getInt("id");
                    String title = jsonObject1.getString("title");
                    String answer1 = jsonObject1.getString("answer_1");
                    String answer2 = jsonObject1.getString("answer_2");
                    String answer3 = jsonObject1.getString("answer_3");
                    String answer4 = jsonObject1.getString("answer_4");
                    String trueAnswer = jsonObject1.getString("true_answer");
                    int points = jsonObject1.getInt("points");
                    int duration = jsonObject1.getInt("duration");
                    String hint=jsonObject1.getString("hint");
//                    PuzzleTaple puzzleTaple=new PuzzleTaple();
//                    puzzleTaple.setId(id);
//                    puzzleTaple.setTitle(title);
//                    puzzleTaple.setAnswer1(answer1);
//                    puzzleTaple.setAnswer2(answer2);
//                    puzzleTaple.setAnswer3(answer3);
//                    puzzleTaple.setAnswer4(answer4);
//                    puzzleTaple.setTrueAnswer(trueAnswer);
//                    puzzleTaple.setPoints(points);
//                    puzzleTaple.setDuration(duration);
//                    viewModel.insertQuestionData(puzzleTaple);


                    JSONObject patternClass = jsonObject1.getJSONObject("pattern");
                    int patternId = patternClass.getInt("pattern_id");
                    String patternName = patternClass.getString("pattern_name");

                    PatternTable pattern = new PatternTable();
                    pattern.setPatternId(patternId);
                    pattern.setPatternName(patternName);

                    viewModel.insertPatternData(pattern);
                    PuzzleTaple puzzleTaple=new PuzzleTaple(title,answer1,answer2,answer3,answer4,trueAnswer,points,duration,patternId,hint,levelNo);
                     viewModel.insertQuestionData(puzzleTaple);

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private String readFromAssets(String fileName) {
        String string = "";
        try {
            InputStream inputStream = getAssets().open(fileName);
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

//    @Override
//    protected void onStop() {
//        super.onStop();
//        mediaPlayer.stop();
//    }
    //
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mediaPlayer.stop();
//    }
}