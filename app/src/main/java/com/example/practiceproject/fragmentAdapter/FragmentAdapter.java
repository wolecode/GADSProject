package com.example.practiceproject.fragmentAdapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.practiceproject.learners_iq.IQFragment;
import com.example.practiceproject.LeaderBoard;
import com.example.practiceproject.learners.LearningFragment;
import com.example.practiceproject.learners_iq.IQLeaders;
import com.example.practiceproject.learners.LearningLeaders;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {

    public ArrayList<LearningLeaders>topLearningLeaders;
    public  ArrayList<IQLeaders>topIQLeaders;
    LeaderBoard board;

     public FragmentAdapter(FragmentActivity fragment){
        super(fragment);
        board=(LeaderBoard)fragment;
        topLearningLeaders=(ArrayList<LearningLeaders>)board.mLearningLeaders;
        topIQLeaders=(ArrayList<IQLeaders>)board.mIqLeaders;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment frag;
        if(position==0){

           frag=new LearningFragment();

        }
        else{
            frag=new IQFragment();
        }
        return frag;

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
