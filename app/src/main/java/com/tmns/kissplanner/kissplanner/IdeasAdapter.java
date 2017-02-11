package com.tmns.kissplanner.kissplanner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class IdeasAdapter extends RecyclerView.Adapter<IdeasAdapter.IdeaViewHolder> {

    ArrayList<String> ideasList;

    public IdeasAdapter(ArrayList<String> ideasList, Context context) {
        this.ideasList = ideasList;
    }

    @Override
    public IdeasAdapter.IdeaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listlayout,parent,false);
        IdeaViewHolder viewHolder=new IdeaViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IdeasAdapter.IdeaViewHolder holder, int position) {
//        holder.image.setImageResource(R.drawable.planetimage);
        String test2 = ideasList.get(position);
        String testpos = ideasList.get(position).toString();
        holder.text.setText(ideasList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return ideasList.size();
    }

    public static class IdeaViewHolder extends RecyclerView.ViewHolder{

        protected ImageView image;
        protected TextView text;

        public IdeaViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image_id);
            text= (TextView) itemView.findViewById(R.id.text_id);
        }
    }
}