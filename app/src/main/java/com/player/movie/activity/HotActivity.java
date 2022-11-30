package com.player.movie.activity;
import static com.player.movie.BaseApplication.getContext;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.player.movie.R;
import com.player.movie.adapter.CategoryRecyclerViewAdapter;
import com.player.movie.adapter.GridRecyclerViewAdapter;
import com.player.movie.adapter.HotRecyclerViewAdapter;
import com.player.movie.entity.MovieEntity;
import com.player.movie.http.RequestUtils;
import com.player.movie.http.ResultEntity;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.List;
import javax.xml.transform.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class HotActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_fragment);
        setModuleTitle();
        getHotMovie();
    }
    private void setModuleTitle(){
        TextView text = findViewById(R.id.category_title).findViewById(R.id.module_title);
        text.setText("Hot Movie");
    }
    private void getHotMovie(){
        Call<ResultEntity> movieData = RequestUtils.getInstance().getHotMovie("Movie");
        movieData.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                List<MovieEntity> movieEntityList = JSON.parseArray(JSON.toJSONString(response.body().getData()), MovieEntity.class);
                HotRecyclerViewAdapter recyclerViewAdapter = new HotRecyclerViewAdapter(movieEntityList,getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                RecyclerView recyclerView = findViewById(R.id.movie_recycler_view);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                System.out.println("error");
            }
        });
    }
}
