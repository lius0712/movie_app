package com.player.movie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.player.movie.R;
import com.player.movie.fragment.HomeFragment;
import com.player.movie.fragment.MovieFragment;
import com.player.movie.fragment.RecommendFragment;
import com.player.movie.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static String uid = null;
    private ViewPager viewPager;
    private List<Fragment> listFragment = new ArrayList<Fragment>();

    LinearLayout homeLinearLayout;
    LinearLayout movieLinearLayout;
    LinearLayout recommendLinearLayout;
    LinearLayout userLinearLayout;

    ImageView homeImg;
    ImageView movieImg;
    ImageView  tvImg;
    ImageView userImg;

    TextView homeText;
    TextView movieText;
    TextView recommendText;
    TextView userText;

    HomeFragment homeFragment;
    MovieFragment movieFragment;
    RecommendFragment recommendFragment;
    UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView(){

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");;

        viewPager = findViewById(R.id.viewpager);

        homeFragment = new HomeFragment();
        movieFragment = new MovieFragment();
        recommendFragment = new RecommendFragment();
        userFragment = new UserFragment();

        listFragment.add(homeFragment);
        listFragment.add(movieFragment);
        listFragment.add(recommendFragment);
        listFragment.add(userFragment);

        homeLinearLayout = findViewById(R.id.home);
        movieLinearLayout = findViewById(R.id.movie);
        recommendLinearLayout = findViewById(R.id.tv);
        userLinearLayout = findViewById(R.id.user_center);

        //导航栏图标
        homeImg = findViewById(R.id.home_img);
        movieImg = findViewById(R.id.movie_img);
        tvImg = findViewById(R.id.tv_img);
        userImg = findViewById(R.id.user_img);

        //导航栏文字
        homeText = findViewById(R.id.home_text);
        movieText = findViewById(R.id.movie_text);
        recommendText = findViewById(R.id.tv_text);
        userText = findViewById(R.id.user_text);

    }

    private void initEvent(){
        FragmentPagerAdapter pageAdapter =new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }
        };

        viewPager.setAdapter(pageAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                int currentItem=viewPager.getCurrentItem();
                tab(currentItem);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        homeLinearLayout.setOnClickListener(this);
        movieLinearLayout.setOnClickListener(this);
        recommendLinearLayout.setOnClickListener(this);
        userLinearLayout.setOnClickListener(this);
    }

    private void tab(int i){
        int color = this.getResources().getColor(R.color.navigate_active);
        resetTab();
        switch (i){
            case 0:{
                homeImg.setImageResource(R.mipmap.icon_home_active);
                homeText.setTextColor(color);
                break;
            }
            case 1:
            {
                movieImg.setImageResource(R.mipmap.icon_movie_active);
                movieText.setTextColor(color);
                movieFragment.initData();
                break;
            }
            case 2:
            {
                tvImg.setImageResource(R.mipmap.icon_tv_active);
                recommendText.setTextColor(color);
                //recommendFragment.initData();
                break;
            }
            case 3:
            {
                userImg.setImageResource(R.mipmap.icon_user_active);
                userText.setTextColor(color);
                userFragment.initData();
                break;
            }
        }
    }

    private void setSelect(int i){
        viewPager.setCurrentItem(i);
    }

    @Override
    public void onClick(View view) {
        resetTab();
        switch (view.getId()){
            case R.id.home:{
                setSelect(0);
                homeImg.setImageResource(R.mipmap.icon_home_active);
                break;
            }
            case R.id.movie:
            {
                setSelect(1);
                movieImg.setImageResource(R.mipmap.icon_movie_active);
                break;
            }
            case R.id.tv:
            {
                setSelect(2);
                tvImg.setImageResource(R.mipmap.icon_tv_active);
                break;
            }
            case R.id.user_center:
            {
                setSelect(3);
                userImg.setImageResource(R.mipmap.icon_user_active);
                break;
            }

        }
    }

    private void resetTab() {
        homeImg.setImageResource(R.mipmap.icon_home);
        movieImg.setImageResource(R.mipmap.icon_movie);
        tvImg.setImageResource(R.mipmap.icon_tv);
        userImg.setImageResource(R.mipmap.icon_user);

        int color = this.getResources().getColor(R.color.navigate);
        homeText.setTextColor(color);
        movieText.setTextColor(color);
        recommendText.setTextColor(color);
        userText.setTextColor(color);
    }

}
