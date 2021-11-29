package com.example.establecimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class list_establecimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_establecimientos);
    }

    public void click_gotoMain(View view){
        Intent siguiente = new Intent(this, MainActivity.class);
        startActivity(siguiente);
    }

    public void click_mostrarGeolocalizacion(View view){
        Intent siguiente = new Intent(this, geolocalizacionActivity.class);
        startActivity(siguiente);
    }

    public void click_mostrarMapas(View view){
        Intent siguiente = new Intent(this, mapasActivity.class);
        startActivity(siguiente);
    }
}