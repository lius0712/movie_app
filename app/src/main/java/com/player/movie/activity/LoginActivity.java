package com.player.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.player.movie.R;
import com.player.movie.entity.UserEntity;
import com.player.movie.http.RequestUtils;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = (EditText)findViewById(R.id.Name);
        password = (EditText)findViewById(R.id.Password);


        Button btn_sign = (Button) findViewById(R.id.signIn);
        btn_sign.setOnClickListener(this);
        TextView text_register = (TextView)findViewById(R.id.register) ;
        text_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signIn:
                try {
                    signIn();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.register:
                register();
                break;
            default:
                break;
        }
    }

    private void register() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void signIn() throws IOException, ExecutionException, InterruptedException {


        if(("").equals(name.getText().toString()) || ("").equals(password.getText().toString())) {
            Toast.makeText(LoginActivity.this, "name or password cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserEntity user = new UserEntity();
        user.setUsername(name.getText().toString());
        user.setPassword(password.getText().toString());

        FutureTask<UserEntity> task = new FutureTask<>(new Callable<UserEntity>() {
            @Override
            public UserEntity call() throws Exception {
                return RequestUtils.getInstance().login(user).execute().body();
            }
        });

        new Thread(task).start();
        UserEntity res = task.get();

        String uid = res.getUserId();

        if(uid == null ) {
            Toast.makeText(LoginActivity.this, "name or password error!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("uid", uid);
            startActivity(intent);
        }

    }

}
