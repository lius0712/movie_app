package com.player.movie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.player.movie.R;
import com.player.movie.adapter.CategoryRecyclerViewAdapter;
import com.player.movie.adapter.GridRecyclerViewAdapter;
import com.player.movie.entity.MovieEntity;
import com.player.movie.http.RequestUtils;
import com.player.movie.http.ResultEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendFragment extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.recommend_fragment,container,false);
        }
        setModuleTitle();
        getRecommend();
        return view;
    }

    private void setModuleTitle(){
        TextView text = view.findViewById(R.id.recommend_title).findViewById(R.id.module_title);
        text.setText(R.string.recommend);
    }


    private void getRecommend(){
        Call<ResultEntity> userData = RequestUtils.getInstance().getRecommend("Movie");
        userData.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                List<MovieEntity> movieEntityList = JSON.parseArray(JSON.toJSONString(response.body().getData()), MovieEntity.class);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                RecyclerView recyclerView = view.findViewById(R.id.recommend_recycler_view);

                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                GridRecyclerViewAdapter gridRecyclerViewAdapter = new GridRecyclerViewAdapter(movieEntityList, getContext(),recyclerView.getWidth());
                recyclerView.setAdapter(gridRecyclerViewAdapter);

            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

}
