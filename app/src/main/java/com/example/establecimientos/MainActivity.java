package com.example.establecimientos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        // Inicializar base de Datos
        db=FirebaseFirestore.getInstance();

    }

    public void click_registrarse(View view){
        TextView
                nombre_user = findViewById(R.id.nombre_user),
                password_user = findViewById(R.id.password_user);

        db.collection("Estalecimiento")
                .document("Estalecimiento1")
                .update("Nombre","fffff","Propietario","eeeeeeeeeeexxee")
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this,"Datos actualizados.", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"Tamos Jodios.", Toast.LENGTH_LONG).show();
                Log.w("MainActivity","Error",e);
            }
        });

        Intent siguiente = new Intent(this, registroActivity.class);
        startActivity(siguiente);
    }

    public void click_entrar(View view){
        Intent siguiente = new Intent(this, list_establecimientoActivity.class);
        startActivity(siguiente);


    }
}
