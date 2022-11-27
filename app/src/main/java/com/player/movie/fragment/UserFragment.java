package com.player.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.player.movie.R;
import com.player.movie.activity.LoginActivity;
import com.player.movie.activity.MainActivity;
import com.player.movie.entity.UserEntity;
import com.player.movie.http.RequestUtils;
import com.player.movie.http.ResultEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment{

    private String uid;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_fragment,container,false);
        uid = new MainActivity().uid;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btn_logout = (Button) getActivity().findViewById(R.id.user_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.user_logout:
                        logout();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void initData(){
        Call<UserEntity> user = RequestUtils.getInstance().getUserData(uid);
        user.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                UserEntity userEntity = response.body();

                TextView userName = view.findViewById(R.id.user_m_name);
                userName.setText(userEntity.getUsername());

                TextView tel = view.findViewById(R.id.user_tel);
                tel.setText(userEntity.getTelephone());

                TextView email = view.findViewById(R.id.user_email);
                email.setText(userEntity.getEmail());

                TextView birth = view.findViewById(R.id.user_birthday);
                birth.setText(userEntity.getBirthday());

                if(userEntity.getSex() == null) {

                } else {
                    TextView sex = view.findViewById(R.id.user_sex);
                    if(userEntity.getSex() == 0) {
                        sex.setText("male");
                    } else {
                        sex.setText("female");
                    }
                }

                TextView role = view.findViewById(R.id.user_role);
                role.setText(userEntity.getRole());

            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {

            }
        });
    }

    private void logout() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
