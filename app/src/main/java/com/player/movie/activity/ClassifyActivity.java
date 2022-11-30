package com.player.movie.activity;

import static com.player.movie.BaseApplication.getContext;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSON;
import com.player.movie.R;
import com.player.movie.adapter.CategoryRecyclerViewAdapter;
import com.player.movie.entity.CategoryEntity;
import com.player.movie.fragment.CategoryFragment;
import com.player.movie.http.RequestUtils;
import com.player.movie.http.ResultEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassifyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_item);
        getAllCategoryListByPageName();
    }
    public void getAllCategoryListByPageName(){
        Call<ResultEntity> categoryListService = RequestUtils.getInstance().getAllCategoryListByPageName("home");
        categoryListService.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                List<CategoryEntity> categoryEntities = JSON.parseArray(JSON.toJSONString(response.body().getData()), CategoryEntity.class);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                for(CategoryEntity category:categoryEntities){
                    CategoryFragment categoryFragment = new CategoryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("category", category.getCategory());
                    bundle.putString("classify", category.getClassify());
                    categoryFragment.setArguments(bundle);
                    transaction.add(R.id.classify_layout, categoryFragment);
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
