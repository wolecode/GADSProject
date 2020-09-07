package com.example.practiceproject.learners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HoursRecyclerAdapter extends RecyclerView.Adapter<HoursRecyclerAdapter.Holder> {
    public List<LearningLeaders> leaders;
    public Context context;

    public HoursRecyclerAdapter(List<LearningLeaders> leaders, Context context){
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
    public HoursRecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate= LayoutInflater.from(parent.getContext());
        View view=inflate.inflate(R.layout.adapter_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursRecyclerAdapter.Holder holder, int position) {
        holder.name.setText(leaders.get(position).name);
        holder.details.setText(leaders.get(position).hours+" "+"Learning hours, "+ leaders.get(position).country);
        Picasso.get()
                .load(leaders.get(position).badgeUrl)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }
}
