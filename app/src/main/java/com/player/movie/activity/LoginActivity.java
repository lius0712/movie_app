package com.player.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.player.movie.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_sign = (Button) findViewById(R.id.signIn);
        btn_sign.setOnClickListener(this);
        TextView text_register = (TextView)findViewById(R.id.register) ;
        text_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signIn:
                signIn();
                break;
            case R.id.register:
                register();
                break;
            default:
                break;
        }
    }

    private void signIn() {
        Intent intent = new Intent(this,MainActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void register() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

}