package com.example.establecimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mapasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
    }
    public void click_gotoListEstablecimientos(View view){
        Intent siguiente = new Intent(this, list_establecimientoActivity.class);
        startActivity(siguiente);
    }
}