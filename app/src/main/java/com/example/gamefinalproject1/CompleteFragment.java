package com.example.gamefinalproject1;

import android.content.Context;
import android.content.DialogInterface;
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

import com.example.gamefinalproject1.databinding.FragmentCompleteBinding;


public class CompleteFragment extends Fragment {
    private static final String ARG_PARAM1 = "question";
    private static final String Correct_Answer = "CorrectAnswer";
    private static final String ARG_hint = "hint";
    private static final String ARG_PARAM = "patternId";
    private static final String ARG_point = "point";
    private static final String ARG_puzzleid = "puzzleid";
    private static final String ARG_levelid = "levelid";
    private static final String ARG_durtion = "durtion";

    private String question;
    private String hint;
    private String CorrectAnswer;
    private  int patternid;
    private int point;
    private  int puzzleid;
    int point_level = 0;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private MediaPlayer True;
    private MediaPlayer False;
    private int durtion;
    Animation bt_up, bt_down;
    Animation top , bottom ;
    private  int levelid;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    com.example.gamefinalproject1.Fragment fragment;
    int correct=0;
    int wrong=0;
    // private boolean sound;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragment=(com.example.gamefinalproject1.Fragment) getContext();
    }

    public CompleteFragment() {
        // Required empty public constructor
    }
    public static CompleteFragment newInstance(String question,int patternid,String hint,String correctAnswer,int point,int puzzleid,int levelid,int durtion) {
        CompleteFragment fragment = new CompleteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, question);
        args.putInt(ARG_PARAM,patternid);
        args.putString(ARG_hint, hint);
        args.putString(Correct_Answer, correctAnswer);
        args.putInt(ARG_point, point);
        args.putInt(ARG_levelid, levelid);

        args.putInt(ARG_durtion, durtion);
        args.putInt(ARG_puzzleid, puzzleid);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(ARG_PARAM1);
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
       FragmentCompleteBinding binding = FragmentCompleteBinding.inflate(inflater, container, false);
        builder = new AlertDialog.Builder(getContext());
        sp= getContext().getSharedPreferences("Points", Context.MODE_PRIVATE);
        editor = sp.edit();
        point_level = sp.getInt("points" + levelid, 0);


        binding.question.setAnimation(bottom);
        binding.answer.setAnimation(bottom);

        bt_up = AnimationUtils.loadAnimation(getContext(), R.anim.bt_animup);
        bt_down = AnimationUtils.loadAnimation(getContext(), R.anim.bt_down);
        binding.answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.txt.getText().toString().equals(CorrectAnswer)) {
                      point++;
                    correct++;
                    Toast.makeText(getContext(), "point"+point, Toast.LENGTH_SHORT).show();
                    sp= getContext().getSharedPreferences("Points", Context.MODE_PRIVATE);
                    editor = sp.edit();
                    point_level = sp.getInt("points" + levelid, 0);
                    True = MediaPlayer.create(getContext(), R.raw.sound_true);
                    True.start();
                    fragment.Intent(correct);
                  //  Toast.makeText(getContext(), "v"+correct, Toast.LENGTH_SHORT).show();
                    binding.answer.setEnabled(false);
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
                    binding.answer.setEnabled(false);
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
            }
        });
        binding.question.setText(question);

        return binding.getRoot();
    }
}