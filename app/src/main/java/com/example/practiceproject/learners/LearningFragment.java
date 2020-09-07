package com.example.practiceproject.learners;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practiceproject.R;
import com.example.practiceproject.learners.HoursRecyclerAdapter;
import com.example.practiceproject.learners.LearningLeaders;

import java.util.List;


public class LearningFragment extends Fragment {

    public static String LEADERS="leaders";
    public  RecyclerView learningRecycler;
    public RecyclerView.LayoutManager layout;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hours, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        learningRecycler=view.findViewById(R.id.learningRecycler);
         List<LearningLeaders>leadersList= bundle.getParcelableArrayList(LEADERS);

        HoursRecyclerAdapter adapt=new HoursRecyclerAdapter(leadersList,getContext());
        learningRecycler.setAdapter(adapt);
        layout= new LinearLayoutManager(getContext());
        learningRecycler.setLayoutManager(layout);

    }
}
