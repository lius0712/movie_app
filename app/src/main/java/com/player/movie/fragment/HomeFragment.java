package com.player.movie.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.player.movie.R;
import com.player.movie.activity.ClassifyActivity;
import com.player.movie.activity.HotActivity;
import com.player.movie.activity.LatestActivity;
import com.player.movie.activity.MainActivity;
import com.player.movie.activity.PrevueActivity;
import com.player.movie.api.Api;
import com.player.movie.entity.CategoryEntity;
import com.player.movie.entity.MovieEntity;
import com.player.movie.entity.UserEntity;
import com.player.movie.http.RequestUtils;
import com.player.movie.http.ResultEntity;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment,container,false);
        addSearchFraction();
        getBannerData();
        getAllCategoryListByPageName();
        ImageView hot = view.findViewById(R.id.hot);
        ImageView preve = view.findViewById(R.id.prevue);
        ImageView latest = view.findViewById(R.id.latest);
        ImageView classify = view.findViewById(R.id.classify);
        hot.setOnClickListener(this);
        preve.setOnClickListener(this);
        latest.setOnClickListener(this);
        classify.setOnClickListener(this);
        return view;
    }
    public void onClick(View v){
        switch ((v.getId())){
            case R.id.hot:
                Intent intentHot = new Intent(getActivity(), HotActivity.class);
                startActivity(intentHot);
                break;
            case R.id.prevue:
                Intent intentPreve = new Intent(getActivity(), PrevueActivity.class);
                startActivity(intentPreve);
                break;
            case R.id.latest:
                Intent intentLatest = new Intent(getActivity(), LatestActivity.class);
                startActivity(intentLatest);
                break;
            case R.id.classify:
                Intent intentClassify = new Intent(getActivity(), ClassifyActivity.class);
                startActivity(intentClassify);
                break;
        }
    }

    private void addSearchFraction(){
        FragmentTransaction transaction =  getFragmentManager().beginTransaction();
        transaction.replace(R.id.home_search_layout,new SearchFragment(getResources().getString(R.string.movie)))
                .commit();
    }
    public void getBannerData(){
        Call<ResultEntity> categoryListService = RequestUtils.getInstance().getCategoryList("carousel","Movie");
        categoryListService.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                List<MovieEntity> movieEntity = JSON.parseArray(JSON.toJSONString(response.body().getData()),MovieEntity.class);
                Banner banner = view.findViewById(R.id.home_banner);
                banner.setAdapter(new BannerImageAdapter<MovieEntity>(movieEntity) {
                    @Override
                    public void onBindView(BannerImageHolder holder, MovieEntity movieEntity, int position, int size) {
                        Glide.with(holder.imageView)
                                .load(Api.HOSTIMG + movieEntity.getLocalImg())
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                    }
                }).setIndicator(new CircleIndicator(getContext())).setBannerRound(20.0f);
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

    public void getAllCategoryListByPageName(){
        Call<ResultEntity> categoryListService = RequestUtils.getInstance().getAllCategoryListByPageName("home");
        categoryListService.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                List<CategoryEntity> categoryEntities = JSON.parseArray(JSON.toJSONString(response.body().getData()), CategoryEntity.class);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                for(CategoryEntity category:categoryEntities){
                    CategoryFragment categoryFragment = new CategoryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("category", category.getCategory());
                    bundle.putString("classify", category.getClassify());
                    categoryFragment.setArguments(bundle);
                    transaction.add(R.id.home_category_layout, categoryFragment);
                }
                transaction.commit();
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                System.out.println("error");
            }
        });
    }
}
