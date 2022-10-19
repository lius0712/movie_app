package com.player.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.player.movie.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
                register();
                break;
            default:
                break;
        }
    }

    private void sign() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void register() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
