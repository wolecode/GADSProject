package com.example.practiceproject.learners_iq;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practiceproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IQFragment extends Fragment {
    public static String LEADERS="IQLeaders";
    public RecyclerView IQRecycler;
    public RecyclerView.LayoutManager layout;

    public IQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i_q, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        IQRecycler=view.findViewById(R.id.IQRecycler);
        List<IQLeaders>leadersList= bundle.getParcelableArrayList(LEADERS);

        IQRecyclerAdapter adapt=new IQRecyclerAdapter(leadersList,getContext());
        IQRecycler.setAdapter(adapt);
        layout= new LinearLayoutManager(getContext());
        IQRecycler.setLayoutManager(layout);
    }
/*
    public static class IQRecyclerAdapter extends RecyclerView.Adapter<IQRecyclerAdapter.Holder> {
        public List<IQLeaders> leaders;
        public Context context;

        public IQRecyclerAdapter(List<IQLeaders> leaders, Context context){
            this.leaders=leaders;
            this.context=context;
        }

        public class Holder extends RecyclerView.ViewHolder{
            public ImageView image;
            public TextView name;
            public TextView details;

            public Holder(View view){
                super(view);
                image=view.findViewById(R.id.image);
                name=view.findViewById(R.id.name);
                details=view.findViewById(R.id.details);
            }
        }



        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflate= LayoutInflater.from(parent.getContext());
            View view=inflate.inflate(R.layout.adapter_layout,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.name.setText(leaders.get(position).name);
            holder.details.setText(leaders.get(position).score+" "+"Skill IQ score, "+ leaders.get(position).country);
            Picasso.get()
                    .load(leaders.get(position).badgeUrl)
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return leaders.size();
        }
    }*/
}