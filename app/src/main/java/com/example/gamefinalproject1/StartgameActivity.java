package com.example.gamefinalproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.gamefinalproject1.databinding.ActivityStartgameBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StartgameActivity extends AppCompatActivity implements Listener{
ActivityStartgameBinding binding;
    AdapterGame adapter ;
    ViewModel module ;
    ArrayList<LevelTable> arrayList ;
    Animation top , bottom ;
    private AdapterGame adapter3;
    private List<LevelTable> levelList;
    private SharedPreferences sh_questionsCount;
    private SharedPreferences.Editor editor;
    private int questionsCount = 0;
    int totalPoints, saveTotalPoint, points_level = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStartgameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBar);
        // set animation
        top = AnimationUtils.loadAnimation(this , R.anim.top);
        bottom = AnimationUtils.loadAnimation(this , R.anim.bottom);

        binding.ReVi.setAnimation(top);
        setTitle("game");
        sh_questionsCount = getBaseContext().getSharedPreferences("Points", Context.MODE_PRIVATE);
        editor = sh_questionsCount.edit();
        totalPoints = sh_questionsCount.getInt("totalPoints", 0);
        binding.pointtotal.setText("إجمالي النقاط : " + totalPoints);
//        try {
//            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
//
//            for (int x = 0; x < jsonArray.length(); x++) {
//                JSONObject jsonObject = (JSONObject) jsonArray.get(x);
//                int level_no = (int) jsonObject.get("level_no");
//                int unlock_points = (int) jsonObject.get("unlock_points");
//                points_level = sh_questionsCount.getInt("points" + level_no, 0);
//                saveTotalPoint = saveTotalPoint + points_level;
//
//                JSONArray jsonArrayContentQuestions = jsonObject.getJSONArray("questions");
//                for (int z = 0; z < jsonArrayContentQuestions.length(); z++) {
//                    JSONObject jsonObjectContentQuestions = (JSONObject) jsonArrayContentQuestions.get(z);
//
//                    int points = (int) jsonObjectContentQuestions.get("points");
//                    questionsCount = questionsCount + points;
//                }
//                editor.putInt("questionsCount" + level_no, questionsCount);
//                editor.apply();
//                questionsCount = 0;
//                levelList.add(new LevelTable(level_no, unlock_points));
//            }
//            if (totalPoints < saveTotalPoint) {
//                editor.putInt("totalPoints", saveTotalPoint);
//                editor.apply();
//                saveTotalPoint = 0;
//            }else {
//                saveTotalPoint = 0;
//            }
//            adapter = new AdapterGame(getBaseContext(), levelList, new AdapterGame.OnItemClickListenerStartPlay() {
//                @Override
//                public void onClickStartPlay(View view, int position, L gameData) {
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_content_main, LevelFragment.newInstance(gameData.getLevel_no()), LEVEL_FRAGMENT).addToBackStack(null).commit();
//
//                }
//            });
//            rv_plays.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        module = new ViewModelProvider(this).get(ViewModel.class);


        module.getAllLevel().observe(this, new Observer<List<LevelTable>>() {
            @Override
            public void onChanged(List<LevelTable> dataOfLevels) {

                arrayList = (ArrayList<LevelTable>) dataOfLevels;

                adapter = new AdapterGame(arrayList , StartgameActivity.this , StartgameActivity.this);

                binding.ReVi.setAdapter(adapter);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(StartgameActivity.this , RecyclerView.VERTICAL ,
                        false);
                binding.ReVi.setLayoutManager(lm);
            }
        });

    }

    @Override
    public void OnClick(int position) {
        int levelNo = arrayList.get(position).getLevelNo();
       // if (arrayList.get(position).getUnlockPoints()>=6){
            Intent intent = new Intent(StartgameActivity.this , LevelgameActivity.class);
            intent.putExtra("levelNo" , levelNo);
            startActivity(intent);
       // }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menustatics,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:

                Intent intent=new Intent(getBaseContext(),HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_static:
                Intent intent2=new Intent(getBaseContext(),StaticsActivity.class);
                startActivity(intent2);
                return true;
        }
        return false;
    }
}