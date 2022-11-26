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

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.player.movie.R;
import com.player.movie.api.Api;
import com.player.movie.entity.MovieEntity;

public class MovieDetailActivity extends AppCompatActivity {

    MovieEntity movieEntity;
    String movieItemString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initData();
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

        System.out.println("************");
        System.out.println(movieEntity.getStar());

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
