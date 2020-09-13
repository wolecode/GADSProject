package com.example.practiceproject.learners;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practiceproject.R;
import com.example.practiceproject.learners.HoursRecyclerAdapter;
import com.example.practiceproject.learners.LearningLeaders;

import java.util.ArrayList;
import java.util.List;


public class LearningFragment extends Fragment {

    public  RecyclerView learningRecycler;
    public RecyclerView.LayoutManager layout;
    private List<LearningLeaders> mLearningLeaders=new ArrayList<>();
    private View mView;
    public HoursRecyclerAdapter adapt;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=inflater.inflate(R.layout.fragment_hours, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       LearningLeadersViewModel leadersViewModel=new ViewModelProvider(getViewModelStore(),ViewModelProvider.AndroidViewModelFactory
               .getInstance(getActivity().getApplication())).get(LearningLeadersViewModel.class);

       leadersViewModel.init();

       leadersViewModel.leaders().observe(this, new Observer<List<LearningLeaders>>() {
           @Override
           public void onChanged(List<LearningLeaders> learningLeaders) {
               if(learningLeaders!=null){
               mLearningLeaders.addAll(learningLeaders);
               Log.i("CONFUSION",String.valueOf(mLearningLeaders.size()));
               }
               adapt.notifyDataSetChanged();
           }
       });

       loadRecyclerView();
    }

    private void loadRecyclerView() {
        learningRecycler=mView.findViewById(R.id.learningRecycler);
        adapt = new HoursRecyclerAdapter(mLearningLeaders,getContext());
        learningRecycler.setAdapter(adapt);
        layout= new LinearLayoutManager(getContext());
        learningRecycler.setLayoutManager(layout);
    }
}
