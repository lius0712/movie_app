package com.player.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.player.movie.R;
import com.player.movie.api.Api;
import com.player.movie.entity.UserEntity;
import com.player.movie.http.RequestService;
import com.player.movie.http.RequestUtils;
import com.player.movie.http.ResultEntity;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText text_name;
    EditText text_email;
    EditText text_password;
    EditText text_passagain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        text_name = (EditText)findViewById(R.id.UserNameEdit);
        text_email = (EditText)findViewById(R.id.EmailEdit);
        text_password = (EditText)findViewById(R.id.PassWordEdit);
        text_passagain = (EditText)findViewById(R.id.PassWordAgainEdit);

        Button sign = (Button) findViewById(R.id.BackLoginButton);
        sign.setOnClickListener(this);
        Button register = (Button) findViewById(R.id.SignUpButton);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BackLoginButton:
                sign();
                break;
            case R.id.SignUpButton:
                try {
                    register();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    private void sign() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void register() throws IOException {

        if(("").equals(text_name.getText().toString()) || ("").equals(text_password.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "name or password cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!text_password.getText().toString().equals(text_passagain.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "password not equal!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserEntity user = new UserEntity();
        user.setUsername(text_name.getText().toString());
        user.setEmail(text_email.getText().toString());
        user.setPassword(text_password.getText().toString());

        Call<UserEntity> res = RequestUtils.getInstance().register(user);
        res.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                Toast.makeText(RegisterActivity.this,"注册成功，请登录",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"请求失败，请重新注册",Toast.LENGTH_SHORT).show();
            }
        });



        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
