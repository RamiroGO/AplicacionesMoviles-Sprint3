package com.example.establecimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
    }
    public void click_gotoListEstablecimientos(View view){
        Intent gotoListEstablecimiento = new Intent(this, listEstablecimientosActivity.class);
        startActivity(gotoListEstablecimiento);
    }
}