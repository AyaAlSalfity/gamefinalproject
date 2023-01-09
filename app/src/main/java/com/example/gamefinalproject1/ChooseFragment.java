package com.example.gamefinalproject1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.gamefinalproject1.databinding.FragmentChooseBinding;


public class ChooseFragment extends Fragment {


    private static final String ARG_Question = "question";
    private static final String ARG_Answer1 = "answer1";
    private static final String ARG_Answer2 = "answer2";
    private static final String ARG_Answer3 = "answer3";
    private static final String ARG_Answer4 = "answer4";
    private static final String Correct_Answer = "CorrectAnswer";
    private static final String ARG_hint = "hint";
    private static final String ARG_PARAM = "patternId";
    private static final String ARG_point = "point";
    private static final String ARG_puzzleid = "puzzleid";
    private static final String ARG_levelid = "levelid";
    private static final String ARG_durtion = "durtion";


    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String hint;
    private String CorrectAnswer;
  private  int patternid;
    private int point;
    private  int levelid;
    private int durtion;
    private  int puzzleid;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private MediaPlayer True;
    private MediaPlayer False;
    Animation bt_up, bt_down;
    Animation top , bottom ;
    com.example.gamefinalproject1.Fragment fragment;
    int correct=0;
    int wrong=0;
    int point_level = 0;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    // private boolean sound;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragment=(com.example.gamefinalproject1.Fragment) getContext();
    }
    public ChooseFragment() {
    }
    public static ChooseFragment newInstance(String question, String answer1,String answer2,String answer3,String answer4,int patternid,String hint,String correctAnswer,int point,int puzzleid,int levelid,int durtion) {
        ChooseFragment fragment = new ChooseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Question, question);
        args.putString(ARG_Answer1, answer1);
        args.putString(ARG_Answer2, answer2);
        args.putString(ARG_Answer3, answer3);
        args.putString(ARG_Answer4, answer4);
        args.putString(ARG_hint, hint);
        args.putString(Correct_Answer, correctAnswer);
        args.putInt(ARG_PARAM, patternid);
        args.putInt(ARG_levelid, levelid);
        args.putInt(ARG_durtion, durtion);
        args.putInt(ARG_point, point);
        args.putInt(ARG_puzzleid, puzzleid);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(ARG_Question);
            answer1 = getArguments().getString(ARG_Answer1);
            answer2 = getArguments().getString(ARG_Answer2);
            answer3 = getArguments().getString(ARG_Answer3);
            answer4 = getArguments().getString(ARG_Answer4);
            hint = getArguments().getString(ARG_hint);
            CorrectAnswer = getArguments().getString(Correct_Answer);
            patternid = getArguments().getInt(ARG_PARAM);
            puzzleid = getArguments().getInt(ARG_puzzleid);
            point = getArguments().getInt(ARG_point);
            durtion = getArguments().getInt(ARG_durtion);

            levelid = getArguments().getInt(ARG_levelid);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChooseBinding binding = FragmentChooseBinding.inflate(inflater , container , false);
        builder = new AlertDialog.Builder(getContext());

        sp= getContext().getSharedPreferences("Points", Context.MODE_PRIVATE);
        editor = sp.edit();
        point_level = sp.getInt("points" + levelid, 0);

        binding.tvQuestion.setAnimation(bottom);
        binding.answer1.setAnimation(bottom);
        binding.answer2.setAnimation(bottom);
        binding.answer3.setAnimation(bottom);
        binding.answer4.setAnimation(bottom);

        bt_up = AnimationUtils.loadAnimation(getContext(), R.anim.bt_animup);
        bt_down = AnimationUtils.loadAnimation(getContext(), R.anim.bt_down);
        binding.tvQuestion.setText(question);
        binding.answer1.setText(answer1);
        binding.answer2.setText(answer2);
        binding.answer3.setText(answer3);
        binding.answer4.setText(answer4);
        binding.answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.answer1.getText().toString().equals(CorrectAnswer)) {
                    correct++;
                     point++;
                    point_level = sp.getInt("points" + levelid, 0);
                    point_level = point_level + point;
                    editor.putInt("points" + levelid, point_level);
                    editor.apply();
                    Toast.makeText(getContext(), "point"+point, Toast.LENGTH_SHORT).show();

                    True = MediaPlayer.create(getContext(), R.raw.sound_true);
                    True.start();
                    fragment.Intent(correct);
                    binding.answer1.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    binding.answer3.setEnabled(false);
                    binding.answer4.setEnabled(false);
                    builder.setTitle("الإجابة صحيحة");
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();


                }else {

                    False = MediaPlayer.create(getContext(), R.raw.sound_false);
                    False.start();
                    wrong++;
                    fragment.Intent2(wrong);
                    binding.answer1.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    binding.answer3.setEnabled(false);
                    binding.answer4.setEnabled(false);
                    builder.setTitle("الإجابة خاطئة");
                    builder.setMessage("الإجابة الصحيحة هي :" + hint);
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();

                }
//                Intent intent=new Intent(getContext(),StartgameActivity.class);
//                startActivity(intent);
            }
        });
        binding.answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.answer2.getText().toString().equals(CorrectAnswer)) {

                    correct++;
                    point++;
                    point_level = sp.getInt("points" + levelid, 0);
                    point_level = point_level + point;
                    editor.putInt("points" + levelid, point_level);
                    editor.apply();
                    Toast.makeText(getContext(), "point"+point, Toast.LENGTH_SHORT).show();

                    True = MediaPlayer.create(getContext(), R.raw.sound_true);
                    True.start();
                    fragment.Intent(correct);
                    binding.answer1.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    binding.answer3.setEnabled(false);
                    binding.answer4.setEnabled(false);
                    builder.setTitle("الإجابة صحيحة");
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();


                }else {
                  wrong++;
                    False = MediaPlayer.create(getContext(), R.raw.sound_false);
                    False.start();
                    fragment.Intent2(wrong);

                    binding.answer1.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    binding.answer3.setEnabled(false);
                    binding.answer4.setEnabled(false);
                    builder.setTitle("الإجابة خاطئة");
                    builder.setMessage("الإجابة الصحيحة هي :" + hint);
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();

                }
//                Intent intent=new Intent(getContext(),StartgameActivity.class);
//                startActivity(intent);
            }
        });
        binding.answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.answer3.getText().toString().equals(CorrectAnswer)) {

                    correct++;
                    point++;
                    point_level = sp.getInt("points" + levelid, 0);
                    point_level = point_level + point;
                    editor.putInt("points" + levelid, point_level);
                    editor.apply();
                    Toast.makeText(getContext(), "point"+point, Toast.LENGTH_SHORT).show();

                    True = MediaPlayer.create(getContext(), R.raw.sound_true);
                    True.start();
                    fragment.Intent(correct);
                    binding.answer1.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    binding.answer3.setEnabled(false);
                    binding.answer4.setEnabled(false);
                    builder.setTitle("الإجابة صحيحة");
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();


                }else {

                    False = MediaPlayer.create(getContext(), R.raw.sound_false);
                    False.start();
                    wrong++;
                    fragment.Intent2(wrong);

                    binding.answer1.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    binding.answer3.setEnabled(false);
                    binding.answer4.setEnabled(false);
                    builder.setTitle("الإجابة خاطئة");
                    builder.setMessage("الإجابة الصحيحة هي :" + hint);
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();

                }
//                Intent intent=new Intent(getContext(),StartgameActivity.class);
//                startActivity(intent);
            }

        });
        binding.answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.answer4.getText().toString().equals(CorrectAnswer)) {

                    correct++;
                    point++;
                    point_level = sp.getInt("points" + levelid, 0);
                    point_level = point_level + point;
                    editor.putInt("points" + levelid, point_level);
                    editor.apply();
                    fragment.Intent(correct);
                    Toast.makeText(getContext(), "point"+point, Toast.LENGTH_SHORT).show();

                    True = MediaPlayer.create(getContext(), R.raw.sound_true);
                    True.start();
                    binding.answer1.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    binding.answer3.setEnabled(false);
                    binding.answer4.setEnabled(false);
                    builder.setTitle("الإجابة صحيحة");
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                         //   Intent intent=new Intent(getContext(),StartgameActivity.class);
                           // startActivity(intent);
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();


                }else {
                    False = MediaPlayer.create(getContext(), R.raw.sound_false);
                    False.start();
                    wrong++;
                    fragment.Intent2(wrong);

                    binding.answer1.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    binding.answer3.setEnabled(false);
                    binding.answer4.setEnabled(false);
                    builder.setTitle("الإجابة خاطئة");
                    builder.setMessage("الإجابة الصحيحة هي :" + hint);
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          //  Intent intent=new Intent(getContext(),HomeActivity.class);
                        //    startActivity(intent);
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();

                }
//                Intent intent=new Intent(getContext(),StartgameActivity.class);
//                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
}