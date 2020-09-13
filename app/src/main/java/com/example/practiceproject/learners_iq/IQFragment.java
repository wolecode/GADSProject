package com.example.practiceproject.learners_iq;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practiceproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class IQFragment extends Fragment {

    private RecyclerView IQRecycler;
    private RecyclerView.LayoutManager layout;
    private List<IQLeaders> mLeadersList=new ArrayList<>();
    private IQRecyclerAdapter adapt;
    private IQViewModel model;
    private View mView;

    public IQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_i_q, container, false);
        return  mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new ViewModelProvider(getViewModelStore(),ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(IQViewModel.class);

        model.init();

        model.leaders().observe(this, new Observer<List<IQLeaders>>() {
             @Override
             public void onChanged(List<IQLeaders> iqLeaders) {
                 if(iqLeaders!=null){
                 mLeadersList.addAll(iqLeaders);

                 }
               adapt.notifyDataSetChanged();
             }
         });

        loadRecyclerView();

    }

    private void loadRecyclerView() {

        IQRecycler = mView.findViewById(R.id.IQRecycler);
        adapt = new IQRecyclerAdapter(mLeadersList, getContext());
        IQRecycler.setAdapter(adapt);
        layout = new LinearLayoutManager(getContext());
        IQRecycler.setLayoutManager(layout);
    }
}