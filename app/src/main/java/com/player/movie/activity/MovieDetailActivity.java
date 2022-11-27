package com.player.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.player.movie.R;
import com.player.movie.adapter.StarRecyclerViewAdapter;
import com.player.movie.api.Api;
import com.player.movie.entity.MovieEntity;
import com.player.movie.entity.MovieStarEntity;
import com.player.movie.http.RequestUtils;
import com.player.movie.http.ResultEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    MovieEntity movieEntity;
    String movieItemString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initData();
        setModuleTitle();
        getStarList();
    }


    private void initData(){
        Intent intent = getIntent();
        movieItemString = intent.getStringExtra("movieItem");

        movieEntity = JSON.parseObject(movieItemString, MovieEntity.class);

        RoundedImageView imageView = findViewById(R.id.detail_movie_img);

        Glide.with(imageView)
                .load(Api.HOSTIMG + movieEntity.getLocalImg())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(imageView);

        TextView movieName = findViewById(R.id.detail_movie_name);
        movieName.setText(movieEntity.getMovieName());

        TextView movieStar = findViewById(R.id.detail_movie_star);

//        System.out.println("************");
//        System.out.println(movieEntity.getStar());

        if(movieEntity.getStar() != null){
            movieStar.setText("Star：" + movieEntity.getStar());
        }else{
            movieStar.setText("Star：nothing");
        }

        TextView descriptionText = findViewById(R.id.detail_description);
        if(movieEntity.getDescription() != null){
            descriptionText.setText(movieEntity.getDescription().trim());
        }else{
            descriptionText.setVisibility(View.GONE);
        }

        LinearLayout scoreLayout = findViewById(R.id.detail_score_layout);
        Double score = movieEntity.getScore();
        if(score != null && score != 0){
            for(int i = 0; i < 5; i++){
                ImageView scoreImageView = (ImageView) scoreLayout.getChildAt(i);
                if(score >= (i+1)*2){
                    scoreImageView.setImageResource(R.mipmap.icon_full_star);
                }else if(score < (i+1)*2 && score >= i*2 + 1){
                    scoreImageView.setImageResource(R.mipmap.icon_half_star);
                }
            }
            TextView scoreText = findViewById(R.id.detail_score);
            scoreText.setText(String.valueOf(score));
        }else{
            findViewById(R.id.detail_no_score).setVisibility(View.VISIBLE);
            scoreLayout.setVisibility(View.GONE);
        }

        TextView plotText = findViewById(R.id.detail_plot);
        if(movieEntity.getPlot() != null){
            plotText.setText("\u3000\u3000" + movieEntity.getPlot());
        }else{
            plotText.setText("\u3000\u3000nothing");
        }

        if(movieEntity.getMovieId() == null){
            findViewById(R.id.detail_movie_play).setVisibility(View.GONE);
        }
    }

    private void setModuleTitle(){
        TextView plotTitle = findViewById(R.id.detail_plot_title).findViewById(R.id.module_title);
        plotTitle.setText(R.string.detail_plot);

        TextView starText = findViewById(R.id.detail_star_title).findViewById(R.id.module_title);
        starText.setText(R.string.detail_star);
    }

    private void getStarList(){
        if(movieEntity.getMovieId() == null){
            findViewById(R.id.detail_star_layout).setVisibility(View.GONE);
            return;
        }
        Call<ResultEntity> call = RequestUtils.getInstance().getStarList(movieEntity.getMovieId().toString());
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                List<MovieStarEntity> movieStarList = JSON.parseArray(JSON.toJSONString(response.body().getData()), MovieStarEntity.class);
                StarRecyclerViewAdapter starRecyclerViewAdapter = new StarRecyclerViewAdapter(movieStarList,MovieDetailActivity.this);
                LinearLayoutManager layoutManager=new LinearLayoutManager(MovieDetailActivity.this);  //LinearLayoutManager中定制了可扩展的布局排列接口，子类按照接口中的规范来实现就可以定制出不同排雷方式的布局了
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                RecyclerView recyclerView = findViewById(R.id.detail_star_recycler_view);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(starRecyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                System.out.println("error");
            }
        });
    }



    public void goPlay(View v){
//        if(movieEntity.getMovieId() != null){
//            Intent intent = new Intent(this, MoviePlayActivity.class);
//            intent.putExtra("movieItem",movieItemString);
//            startActivity(intent);
//        }else{
//            Toast.makeText(getApplicationContext(), R.string.detail_no_play, Toast.LENGTH_SHORT).show();
//        }
    }

}
