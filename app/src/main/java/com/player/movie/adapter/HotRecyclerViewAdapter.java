package com.player.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.player.movie.R;
import com.player.movie.activity.MovieDetailActivity;
import com.player.movie.api.Api;
import com.player.movie.entity.MovieEntity;

import java.util.List;

public class HotRecyclerViewAdapter extends RecyclerView.Adapter<HotRecyclerViewAdapter.ViewHolder> implements View.OnClickListener{

    private List<MovieEntity> movieEntityList;
    private Context context;
    public HotRecyclerViewAdapter(List<MovieEntity> movieEntityList, Context context){
        this.movieEntityList = movieEntityList;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String movieJSONString = JSON.toJSONString(v.getTag());
        intent.putExtra("movieItem",movieJSONString);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public HotRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotRecyclerViewAdapter.ViewHolder holder, int position) {
        String path = Api.HOSTIMG + movieEntityList.get(position).getLocalImg();
        Glide.with(context).load(path).into(holder.imageView);
        holder.textView.setText(movieEntityList.get(position).getMovieName());
        holder.itemView.setTag(movieEntityList.get(position));
//        if(position == movieEntityList.size() - 1){
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(0,0,0,0);
//            holder.itemView.setLayoutParams(layoutParams);
//        }
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return movieEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public final RoundedImageView imageView;
        public final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hot_img);
            textView = itemView.findViewById(R.id.hot_name);
        }
    }

}


