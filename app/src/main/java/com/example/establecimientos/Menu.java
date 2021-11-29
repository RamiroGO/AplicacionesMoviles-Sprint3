package com.example.establecimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    public void Ventana_Nuevo (View v) {
        Intent Nuevo = new Intent(this,Nuevo.class);
        startActivity(Nuevo);
    }

}