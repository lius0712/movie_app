package com.player.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.player.movie.R;
import com.player.movie.api.Api;
//import com.player.movie.entity.EditEntity;
//import com.player.movie.state.State;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragment);
        initUI();
        setOnClickListener();
    }

    private void initUI(){
        RoundedImageView avater = findViewById(R.id.user_avater);
        //Glide.with(this).load(Api.HOST + State.userEntity.getAvater()).into(avater);

        TextView userName = findViewById(R.id.user_m_name);
        //userName.setText(State.userEntity.getUsername());
        userName.setTag(true);// 必填

        TextView tel = findViewById(R.id.user_tel);
        //tel.setText(State.userEntity.getTelephone());
        tel.setTag(false);// 非必填

        TextView email = findViewById(R.id.user_email);
        //email.setText(State.userEntity.getEmail());
        email.setTag(true);// 必填

        TextView birthday = findViewById(R.id.user_birthday);
        //birthday.setText(State.userEntity.getBirthday());
        birthday.setTag(false);// 非必填

        TextView sex = findViewById(R.id.user_sex);
        //sex.setText(State.userEntity.getSex());
        sex.setTag(true);// 必填

        TextView sign = findViewById(R.id.user_role);
        //sign.setText(State.userEntity.getSign());
        sign.setTag(false);// 必填

    }

    private void setOnClickListener(){
        findViewById(R.id.user_name_layout).setOnClickListener(this);// 昵称
        findViewById(R.id.user_tel_layout).setOnClickListener(this);// 电话
        findViewById(R.id.user_email_layout).setOnClickListener(this);// 邮箱
        findViewById(R.id.user_birthday_layout).setOnClickListener(this);// 出生年月日
        findViewById(R.id.user_sex_layout).setOnClickListener(this);// 性别
        findViewById(R.id.user_sign_layout).setOnClickListener(this);// 签名
        findViewById(R.id.user_logout).setOnClickListener(this);// 退出登录
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_name_layout:
            case R.id.user_tel_layout:
            case R.id.user_email_layout:
            case R.id.user_birthday_layout:
            case R.id.user_sex_layout:
            case R.id.user_sign_layout:
                useEdit(v);
                break;
            case R.id.user_logout:
                logout();
                break;
        }
    }

    private void useEdit(View v){
        LinearLayout ly = (LinearLayout)v;
        TextView nameTextView = (TextView) ly.getChildAt(0);
        String title = nameTextView.getText().toString();
        TextView valueTextView = (TextView) ly.getChildAt(1);
        String value = valueTextView.getText().toString();
        Boolean require = (Boolean) valueTextView.getTag();
        //Intent intent = new Intent(this,EditActivity.class);
        //intent.putExtra("editEntity",JSON.toJSONString(new EditEntity(title,value,require)));
        //startActivity(intent);
    }

    private void logout(){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// 前面所有页面置空
        startActivity(intent);
    }
}
