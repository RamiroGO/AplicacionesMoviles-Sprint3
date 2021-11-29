package com.example.establecimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class registroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_form);
    }

    public void click_gotoMain(View view){
        Intent siguiente = new Intent(this, MainActivity.class);
        startActivity(siguiente);
    }
}