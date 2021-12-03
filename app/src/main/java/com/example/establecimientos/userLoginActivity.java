package com.example.establecimientos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.establecimientos.Models.Usuario;
import com.google.firebase.firestore.FirebaseFirestore;

public class userLoginActivity extends AppCompatActivity {
    // Variables de Clase
    // - Base de datos de Firestores
    private FirebaseFirestore db_firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        // Inicializar base de Datos
        db_firestore = FirebaseFirestore.getInstance();
    }

    public void click_registrarse(View view){
        Intent _siguiente = new Intent(this, userRegisterActivity.class);
        startActivity(_siguiente);
    }

    public void click_entrar(View view){
        // Variables Locales
        Usuario
                usuarioAcceso;
        TextView
                nombre_user,
                contraseña_user;

        // Recibir datos de la interfaz
        nombre_user = findViewById(R.id.nombre_user);
        contraseña_user = findViewById(R.id.password_user);

        // Crear Objeto con los datos recibidos de la Interfaz.
        usuarioAcceso = new Usuario(nombre_user, contraseña_user);

        // Enviar datos de autenticación a la Base de Datos de Firestore
        db_firestore.collection("Accesos")
                .add(usuarioAcceso)
                .addOnSuccessListener(
                        documentReference -> Log.d(
                                "Acceso",
                                "Acceso Numero: "
                                        + documentReference.getId()))
                .addOnFailureListener(
                        e -> Log.w(
                                "Acceso",
                                "Error en Acceso", e));

        Intent siguiente = new Intent(this, showListEstablecimientosActivity.class);
        startActivity(siguiente);
    }
}