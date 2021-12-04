package com.example.establecimientos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.establecimientos.Models.Establecimiento;
import com.google.firebase.firestore.FirebaseFirestore;

public class newEstablecimientoActivity extends AppCompatActivity {
    FirebaseFirestore db_firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_establecimiento);

        // Inicializar base de Datos
        db_firestore = FirebaseFirestore.getInstance();
    }

    public void btnNewEstablecimiento(View view) {
        // Recibir datos de la interfaz
        TextView
                Nombre = findViewById(R.id.newTxtNombreEstablecimiento),
                Direccion = findViewById(R.id.newTxtDireccion),
                Propietario = findViewById(R.id.newTxtPropietario),
                Telefono = findViewById(R.id.newTxtTelefono);

        Establecimiento NewEstablecimiento = new Establecimiento(
                Nombre,
                Direccion,
                Telefono,
                Propietario,
                R.drawable.icono_negocio1);

        // Enviar datos de autenticación a la Base de Datos de Firestore
        db_firestore.collection("Establecimientos")
                .add(NewEstablecimiento)
                .addOnSuccessListener(
                        documentReference -> {
                            // Mensaje Emergente
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Establecimiento Creado",
                                    Toast.LENGTH_LONG).show();
                            // Cambiar de Activity a la anterior
                            gotoListEstablecimiento();
                        })
                .addOnFailureListener(
                        e -> Toast.makeText(
                                getApplicationContext(),
                                "Error al crear nuevo Establecimiento",
                                Toast.LENGTH_LONG).show());
    }

    public void btnGotoListClick(View view) {
        startActivity(
                new Intent(
                        this,
                        showListEstablecimientosActivity.class));
    }

    /**
     * Regresar a la Lista de Establecimientos
     */
    private void gotoListEstablecimiento() {
        Intent gotoListEstablecimiento = new Intent(
                newEstablecimientoActivity.this,
                showListEstablecimientosActivity.class);
        startActivity(gotoListEstablecimiento);
    }
}