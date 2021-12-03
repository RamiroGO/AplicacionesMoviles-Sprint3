package com.example.establecimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class geolocalizacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocations);
    }

    public void click_volver(View view){
        Intent siguiente = new Intent(this, showListEstablecimientosActivity.class);
        startActivity(siguiente);
    }
}