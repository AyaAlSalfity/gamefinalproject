package com.example.gamefinalproject1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.gamefinalproject1.databinding.FragmentCompleteBinding;
import com.example.gamefinalproject1.databinding.FragmentTrueOrFaulseBinding;

import java.util.ArrayList;


public class TrueOrFaulseFragment extends Fragment {
//    interface Send{
//        void  sendto(int correct,int wrong);
//    }

   // Send send;
    private static final String ARG_PARAM1 = "question";
    private static final String Correct_Answer = "CorrectAnswer";
    private static final String ARG_Answer = "trueAnswer";

    private static final String ARG_hint = "hint";
    private static final String ARG_PARAM2 = "patternId";
    private static final String ARG_point = "point";
    private static final String ARG_puzzleid = "puzzleid";
    private static final String ARG_levelid = "levelid";
    private static final String ARG_durtion = "durtion";
    private static final String ARG_points = "points";


    private String question;
    private String hint;
    private String CorrectAnswer;
    private  int patternid;
    private int point=0;
    private  int puzzleid;
    private  int levelid;
    private int durtion;
    private  int points=0;
    String trueanswer;
    ViewPager viewPager;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private MediaPlayer True;
    private MediaPlayer False;
    private int level_no;
    int point_level = 0;
    com.example.gamefinalproject1.Fragment fragment;
    int correct=0;
    int wrong=0;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

   // private boolean sound;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
  //      send=(Send)context;
        fragment=(com.example.gamefinalproject1.Fragment) getContext();
    }

    public TrueOrFaulseFragment() {
        // Required empty public constructor
    }

    public static TrueOrFaulseFragment newInstance (String question,int patternid,String hint,String correctAnswer,int point,int puzzleid,int levelid,int durtion){
        TrueOrFaulseFragment fragment = new TrueOrFaulseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, question);
        args.putString(ARG_hint, hint);
        args.putString(Correct_Answer, correctAnswer);
        args.putInt(ARG_PARAM2, patternid);
        args.putInt(ARG_point, point);
        args.putInt(ARG_puzzleid, puzzleid);
        args.putInt(ARG_levelid, levelid);
        //  args.putInt(ARG_points,points);
   //     args.putInt(ARG_Answer,true_Answer);
        args.putInt(ARG_durtion, durtion);

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
            patternid = getArguments().getInt(ARG_PARAM2);
            puzzleid = getArguments().getInt(ARG_puzzleid);
            point = getArguments().getInt(ARG_point);
            durtion = getArguments().getInt(ARG_durtion);
             //points =getArguments().getInt(ARG_points);
            levelid = getArguments().getInt(ARG_levelid);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       FragmentTrueOrFaulseBinding binding = FragmentTrueOrFaulseBinding.inflate(inflater, container, false);

        builder = new AlertDialog.Builder(getContext());
        sp= getContext().getSharedPreferences("Points", Context.MODE_PRIVATE);
        editor = sp.edit();
        point_level = sp.getInt("points" + levelid, 0);

        binding.question.setText(question);
     binding.answer.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (binding.answer.getText().toString().equals(CorrectAnswer)) {
               correct++;
                point++;
                 point_level = sp.getInt("points" + levelid, 0);
                 point_level = point_level + point;
                 editor.putInt("points" + levelid, point_level);
                 editor.apply();
                 Toast.makeText(getContext(), "point"+point, Toast.LENGTH_SHORT).show();
                 True = MediaPlayer.create(getContext(), R.raw.sound_true);
                     True.start();
                 binding.answer.setEnabled(false);
                 binding.answer2.setEnabled(false);
                 builder.setTitle("الإجابة صحيحة");
                 fragment.Intent(correct);
                 builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                    //     viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

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
                 binding.answer2.setEnabled(false);
                 builder.setTitle("الإجابة خاطئة");
                 builder.setMessage("الإجابةالصحيحة هي :" + hint);
                 builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                      //   viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                     }
                 });
                 alertDialog = builder.create();
                 alertDialog.show();

             }
//             Intent intent=new Intent(getContext(),StaticsActivity.class);
//             intent.putExtra("correct",correct);
//             intent.putExtra("wrong",wrong);
//             startActivity(intent);

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
                  //  Toast.makeText(getContext(), "r"+point_level, Toast.LENGTH_SHORT).show();
                    editor.apply();
                    Toast.makeText(getContext(), "point"+point, Toast.LENGTH_SHORT).show();

                    True = MediaPlayer.create(getContext(), R.raw.sound_true);
                    True.start();
                    binding.answer.setEnabled(false);
                    binding.answer2.setEnabled(false);
                    builder.setTitle("الإجابة صحيحة");
                    fragment.Intent(correct);
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
                    binding.answer2.setEnabled(false);
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
//                Intent intent=new Intent(getContext(),StaticsActivity.class);
//                intent.putExtra("correct",correct);
//                intent.putExtra("wrong",wrong);
//                startActivity(intent);
//                ViewGroup viewGroup=findViewById(android.R.id.content);
//                AlertDialog.Builder builder=new AlertDialog.Builder(Test.this);
//                View view1= LayoutInflater.from(Test.this).inflate(R.layout.dialog_trueanswer,viewGroup,false);
//                builder.setCancelable(false);
//                builder.setView(view1);
//                final   AlertDialog alertDialog=builder.create();
//                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                bt_cancle=view1.findViewById(R.id.cancel);
//                bt_cancle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        alertDialog.dismiss();
//                    }
//                });
//                alertDialog.show();
//            }else {
//                wrong++;
//                player=MediaPlayer.create(Test.this,R.raw.wrong);
//                player.start();
//                Toast.makeText(Test.this, "Wrong!correct answer :"+questionList.get(currentQuestion).getCorrectAnswer(), Toast.LENGTH_SHORT).show();
//                ViewGroup viewGroup=findViewById(android.R.id.content);
//                AlertDialog.Builder builder=new AlertDialog.Builder(Test.this);
//                View view2= LayoutInflater.from(Test.this).inflate(R.layout.dialog_falseanswer,viewGroup,false);
//                builder.setCancelable(false);
//                builder.setView(view2);
//                final   AlertDialog alertDialog=builder.create();
//                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                bt_cancle2=view2.findViewById(R.id.cancel_button);
//                hint=view2.findViewById(R.id.hint);
//                String hinnt= questionList.get(currentQuestion).getHint();
//                hint.setText(hinnt);
//                bt_cancle2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        alertDialog.dismiss();
//                    }
//                });
//                alertDialog.show();
            }
        });

//        binding.numberpoint.setText("number point:"+ point);
//        binding.numberlevel.setText("number level:"+levelid );
//        binding.numberpuzzel.setText("number puzzle:"+ puzzleid);
//        binding.numberquestion.setText("number question:"+ puzzleid);
//             binding.durtion.setText("durtion:"+ durtion);
        //   send.sendto(wrong,correct);
        return binding.getRoot();
    }
}