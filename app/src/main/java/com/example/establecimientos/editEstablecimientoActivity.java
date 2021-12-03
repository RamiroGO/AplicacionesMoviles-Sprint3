package com.example.establecimientos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class editEstablecimientoActivity extends AppCompatActivity {
    String Id;
    TextView
            Nombre,
            Direccion,
            Propietario,
            Telefono;
    ImageView ImgEstable;

    FirebaseFirestore db_firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_establecimiento);
        // Variables Locales
        int selImagen;

        // Inicializar base de Datos
        db_firestore = FirebaseFirestore.getInstance();

        //Enlazar elementos de la View
        Nombre = (TextView) findViewById(R.id.editTextNombreEstablecimiento);
        Direccion = (TextView) findViewById(R.id.editTextDireccion);
        Propietario = (TextView) findViewById(R.id.editTextPersonName);
        Telefono = (TextView) findViewById(R.id.editTextPhone);
        ImgEstable = (ImageView) findViewById(R.id.imageEstablecimiento);

        // Recibir valores de la anterior activity
        Nombre.setText(getIntent().getStringExtra("nombre_establecimiento"));
        Direccion.setText(getIntent().getStringExtra("direcc_establecimiento"));
        Propietario.setText(getIntent().getStringExtra("nombre_propietario"));
        Telefono.setText(getIntent().getStringExtra("telefono"));
        // Recibir el Id del Establecimiento previamente seleccionado
        Id = getIntent().getStringExtra("Id");
        // Poblar Imagen
        selImagen = getIntent().getIntExtra("image", 0);
        ImgEstable.setImageResource(selImagen);
    }

    public void btnDeleteClick(View view) {
        AlertDialog.Builder AnsUser =
                new AlertDialog.Builder(this)
                        .setMessage("¿Desea Eliminar este Establecimiento?");

        AnsUser.setPositiveButton("Si", ansUserPositive());
        AnsUser.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AnsUser.show();
    }

    @NonNull
    private DialogInterface.OnClickListener ansUserPositive() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db_firestore.collection("Establecimientos")
                        .document(Id)
                        .delete()
                        .addOnCompleteListener(
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Establecimiento Eliminado", Toast.LENGTH_LONG).show();
                                        gotoListEstablecimiento();
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "No se pudo Eliminar el Establecimiento",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                        );
            }
        };
    }

    public void btnEditClick(View view) {
        // Recibir datos de la interfaz
        Nombre = findViewById(R.id.editTextNombreEstablecimiento);
        Direccion = findViewById(R.id.editTextDireccion);
        Propietario = findViewById(R.id.editTextPersonName);
        Telefono = findViewById(R.id.txtTelefono);

        // Enviar datos de autenticación a la Base de Datos de Firestore
        db_firestore.collection("Establecimientos")
                .document(Id)
                .update(
                        "nombre", Nombre,
                        "direccion", Direccion,
                        "propietario", Propietario,
                        "telefono", Telefono)
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Establecimiento Actualizado",
                                        Toast.LENGTH_LONG).show();
                                gotoListEstablecimiento();
                            }
                        }
                )
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Error en la Actualización del Establecimiento",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
    }

    /**
     * Regresar a la Lista de Establecimientos
     */
    private void gotoListEstablecimiento() {
        Intent gotoListEstablecimiento = new Intent(
                editEstablecimientoActivity.this,
                showListEstablecimientosActivity.class);
        startActivity(gotoListEstablecimiento);
    }
}