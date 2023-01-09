package com.example.gamefinalproject1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class AdapterLevel extends FragmentStateAdapter {

    ArrayList<Fragment> arrayList ;

    public AdapterLevel(@NonNull FragmentActivity fragmentActivity , ArrayList<Fragment> arrayList) {
        super(fragmentActivity);
        this.arrayList = arrayList ;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}