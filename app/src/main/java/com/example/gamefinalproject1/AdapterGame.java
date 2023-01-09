package com.example.gamefinalproject1;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamefinalproject1.databinding.ItemStartPlayBinding;

import java.util.ArrayList;

public class AdapterGame extends RecyclerView.Adapter<AdapterGame.DataViewHolder> {

    ArrayList<LevelTable> arrayList ;
    Context context ;
    Listener listener;
 //ItemClickListener itemClickListener;
    SharedPreferences totalScore  ;
    LevelTable levelTable;

    public AdapterGame(ArrayList<LevelTable> arrayList , Context context , Listener listener) {
        this.arrayList = arrayList;
        this.context = context ;
        this.listener = listener ;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStartPlayBinding binding = ItemStartPlayBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false);

        return new DataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
      /*  totalScore = context.getSharedPreferences("point" , Context.MODE_PRIVATE);
         int point = totalScore.getInt("totalScore" ,0 );
        int levelpoint = totalScore.getInt("Score" ,0 );*/
        int pos = position ;
        holder.tvlevel.setText("Level" +" "+ arrayList.get(position).getLevelNo());
        holder.txpoint.setText("Pass Score"  +" "+ arrayList.get(position).getUnlockPoints());
//        levelTable = arrayList.get(holder.getAdapterPosition());
//
//        int level_no = arrayList.get(position).getLevelNo();
//        int unlock_points = arrayList.get(position).getUnlockPoints();
//
//        totalScore = context.getSharedPreferences("Points", Context.MODE_PRIVATE);
//        int totalPoints = totalScore.getInt("totalPoints", 0);
//        int points_level = totalScore.getInt("points" + level_no, 0);
//        int questionsCount_level = totalScore.getInt("questionsCount" + level_no, 1);
//        float rating =(float) points_level / questionsCount_level * 100;
//
//
//        holder.tvlevel.setText("رقم المرحلة : " + level_no);
//        holder.golevel.setText("عدد النقاط المطلوبة : " + unlock_points);
//        holder.txvrating.setText("تقييم المرحلة : " + rating +" %");
//
//        holder.tvlevel.setTag(totalPoints);
//        holder.img.setTag(levelTable);
//
//        if (totalPoints >= unlock_points) {
//            holder.img.setImageResource(R.drawable.ic_lock_open);
//        } else {
//            holder.img.setImageResource(R.drawable.ic_lock);
//        }

/*if (point >= levelpoint){
    holder.img.setImageResource(R.drawable.notification);
} else {
    holder.img.setImageResource(R.drawable.home);
}
holder.img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        listener.OnClick(pos);
    }
});*/
        holder.golevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnClick(pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{
        ImageView img  ;
        TextView tvlevel , txpoint , txvrating;
        Button golevel;

        public DataViewHolder(@NonNull ItemStartPlayBinding binding) {
            super(binding.getRoot());

            img = binding.img ;
            tvlevel= binding.textViewlevel ;
            txpoint = binding.passScor ;
            txvrating=binding.rating;
            golevel=binding.btLevel;
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    LevelTable levelTable = (LevelTable) img.getTag();
//                    int totalPoints = (int) tvlevel.getTag();
//                    if (totalPoints >= levelTable.getUnlockPoints()) {
//                        itemClickListener.Click(v, getAdapterPosition(), levelTable);
//                    }
//                }
//            });
        }
    }
//    public interface ItemClickListener {
//        void Click(View view, int position, LevelTable gameData);
//
//    }
}

