package com.example.establecimientos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class editEstablecimientoActivity extends AppCompatActivity {
    // Variables de Clase
    String IdDocument_Establecimiento;
    TextView
            Nombre,
            Direccion,
            Propietario,
            Telefono;
    ImageView ImgEstable;
    int refImagen;

    FirebaseFirestore db_firestore;
    // Constructor del Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_establecimiento);

        // Variables Locales
        Bundle _extras = getIntent().getExtras();

        // Inicializar base de Datos
        db_firestore = FirebaseFirestore.getInstance();

        //Enlazar elementos de la View
        Nombre = findViewById(R.id.editTextNombreEstablecimiento);
        Direccion = findViewById(R.id.editTextAddress);
        Propietario = findViewById(R.id.editTextPersonName);
        Telefono = findViewById(R.id.editTextPhone);
        ImgEstable = findViewById(R.id.imageEstablecimiento);

        // Recibir valores de la anterior activity
        Nombre.setText(_extras.getString("nombre_establecimiento"));
        Direccion.setText(_extras.getString("direcc_establecimiento"));
        Propietario.setText(_extras.getString("nombre_propietario"));

        // Convertir valor numérico a String y enviar a la View
        Telefono.setText(String.valueOf(_extras.getInt("telefono",0)));
        // Recibir el Id del Establecimiento previamente seleccionado
        IdDocument_Establecimiento = _extras.getString("idDocument_establecimiento");

        // Poblar Imagen
        refImagen = _extras.getInt("image", 0);
        ImgEstable.setImageResource(refImagen);
    }

    // Eventos de Botones
    public void btnDeleteClick(View view) {
        AlertDialog.Builder AnsUser =
                new AlertDialog.Builder(this)
                        .setMessage("¿Desea Eliminar este Establecimiento?");

        AnsUser.setPositiveButton("Si", ansUserPositive());
        AnsUser.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        AnsUser.show();
    }

    public void btnEditClick(View view) {
        // Recibir datos de la interfaz
        Nombre = findViewById(R.id.editTextNombreEstablecimiento);
        Direccion = findViewById(R.id.editTextAddress);
        Propietario = findViewById(R.id.editTextPersonName);
        Telefono = findViewById(R.id.editTextPhone);

        // Enviar datos de autenticación a la Base de Datos de Firestore
        db_firestore.collection("Establecimientos")
                .document(IdDocument_Establecimiento)
                .update(
                        "nombre", Nombre.getText().toString(),
                        "direccion", Direccion.getText().toString(),
                        "propietario", Propietario.getText().toString(),
                        "telefono", Integer.valueOf(Telefono.getText().toString()),
                        "imagen",  refImagen)
                .addOnCompleteListener(
                        task -> {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Establecimiento Actualizado",
                                    Toast.LENGTH_LONG).show();
                            gotoListEstablecimiento();
                        }
                )
                .addOnFailureListener(
                        e -> Toast.makeText(
                                getApplicationContext(),
                                "Error en la Actualización del Establecimiento",
                                Toast.LENGTH_LONG).show());
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
    @NonNull
    private DialogInterface.OnClickListener ansUserPositive() {
        return (dialog, which) -> db_firestore.collection("Establecimientos")
                .document(IdDocument_Establecimiento)
                .delete()
                .addOnCompleteListener(
                        task -> {
                            Toast.makeText(getApplicationContext(), "Establecimiento Eliminado", Toast.LENGTH_LONG).show();
                            gotoListEstablecimiento();
                        })
                .addOnFailureListener(
                        e -> Toast.makeText(
                                getApplicationContext(),
                                "No se pudo Eliminar el Establecimiento",
                                Toast.LENGTH_LONG).show()
                );
    }

}