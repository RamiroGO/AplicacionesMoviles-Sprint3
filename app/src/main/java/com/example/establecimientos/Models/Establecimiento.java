package com.example.establecimientos.Models;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;

public class Establecimiento {
    // Las variables deben declararse como públicas
    // porque además de sus valores, sus nombres se usarán como variables a enviar
    public String nombre;
    public String direccion;
    public String telefono;
    public String propietario;
    public int image;

    /**
     * Constructor Manual
     */
    public Establecimiento(TextView nombre, TextView direccion, TextView telefono, TextView propietario, int image) {
        this.nombre = nombre.getText().toString();
        this.direccion = direccion.getText().toString();
        this.telefono = telefono.getText().toString();
        this.propietario = propietario.getText().toString();
        this.image = image;
    }

    /**
     * Convertir dato recibido en un objeto de tipo Establecimiento
     */
    public Establecimiento(@NonNull QueryDocumentSnapshot dato) {
        this.nombre = dato.getString("nombre");
        this.direccion = dato.getString("direccion");
        this.telefono = dato.getString("telefono");
        this.propietario = dato.getString("propietario");

        String stringImg = dato.getString("imagen");
        if (stringImg == null) {
            this.image = 2131165315;
        } else {
            this.image = Integer.parseInt(stringImg);
        }
    }
}
