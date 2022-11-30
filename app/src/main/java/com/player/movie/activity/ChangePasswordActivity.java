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

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{
    EditText Password;
    EditText PasswordConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_2);
        Password = (EditText) findViewById(R.id.Password1);
        PasswordConfirm = (EditText) findViewById(R.id.Password2);
        Button previous = (Button) findViewById(R.id.Previous);
        previous.setOnClickListener(this);
        TextView confirm = (TextView)findViewById(R.id.Confirm) ;
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Previous:
                previous();
                break;
            case R.id.Confirm:
                try {
                    confirm();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    private void previous() {
        Intent intent = new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
    private void confirm() throws IOException, ExecutionException, InterruptedException {
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        if(Password.getText().toString().equals(PasswordConfirm.getText().toString())){

        }else {
            Toast.makeText(ChangePasswordActivity.this, "The two password entries are not equal!", Toast.LENGTH_SHORT).show();
        }
    }
}
