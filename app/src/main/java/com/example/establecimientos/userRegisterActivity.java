package com.example.establecimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class userRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registro);
    }

    public void click_gotoMain(View view){
        Intent siguiente = new Intent(this, userLoginActivity.class);
        startActivity(siguiente);
    }
}