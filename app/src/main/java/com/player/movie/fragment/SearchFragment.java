package com.player.movie.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.player.movie.R;
import com.player.movie.activity.SearchActivity;
import com.player.movie.api.Api;
import com.player.movie.entity.MovieEntity;
import com.player.movie.entity.UserEntity;
import com.player.movie.http.RequestUtils;
import com.player.movie.http.ResultEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    View view;
    LinearLayout avaterLayout;
    String classify;
    MovieEntity movieEntity;
    List<MovieEntity> movie;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment,container,false);
        initData();
        getKeyWord();
        addSearchClickListener();
        return view;
    }


    public SearchFragment(String classify){
        this.classify = classify;
    }


    private void initData(){
        avaterLayout = view.findViewById(R.id.avater_layout);
        avaterLayout.setVisibility(View.VISIBLE);
        //Glide.with(getContext()).load(Api.HOSTIMG + UserEntity.getAvater()).into((RoundedImageView)view.findViewById(R.id.avater));
    }

    private void addSearchClickListener(){
        LinearLayout searchLayout = view.findViewById(R.id.search_layout);
        searchLayout.setOnClickListener(listener->{
            Context context = getContext();
            Intent intent = new Intent(context, SearchActivity.class);
            intent.putExtra("movieItem", JSON.toJSONString(movieEntity));
            context.startActivity(intent);
        });
    }

    public void getKeyWord(){
        Call<ResultEntity> getKeyWordService = RequestUtils.getInstance().getKeyWord(classify);
        getKeyWordService.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                //movieEntity = JSON.parseObject(JSON.toJSONString(response.body().getData()), MovieEntity.class);
                movie = JSON.parseArray(JSON.toJSONString(response.body().getData()),MovieEntity.class);
                TextView textView = avaterLayout.findViewById(R.id.search_key);
                textView.setText(movie.get(0).getMovieName());
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {

            }
        });
    }

}


