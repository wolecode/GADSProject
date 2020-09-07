package com.example.practiceproject.learners_iq;

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

public class IQRecyclerAdapter extends RecyclerView.Adapter<IQRecyclerAdapter.Holder> {
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
    public void onBindViewHolder(@NonNull IQRecyclerAdapter.Holder holder, int position) {
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
}

