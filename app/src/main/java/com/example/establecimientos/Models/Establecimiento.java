package com.example.establecimientos.Models;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;

public class Establecimiento {
    /**
     * Variables de Clase
     */
    // Las variables deben declararse como públicas
    // porque además de sus valores, sus nombres se usarán como variables a enviar
    private String IdDocument;
    public String nombre;
    public String direccion;
    public int telefono;
    public String propietario;
    public int imagen;

    /**
     * Propiedades
     */
    public String IdDocument() {
        return IdDocument;
    }

    /**
     * Constructores
     */
    public Establecimiento(TextView nombre, TextView direccion, TextView telefono, TextView propietario, int imagen) {
        this.nombre = nombre.getText().toString();
        this.direccion = direccion.getText().toString();
        this.telefono = Integer.parseInt(telefono.getText().toString());
        this.propietario = propietario.getText().toString();
        this.imagen = imagen;
    }

    /**
     * Constructor: Convertir dato recibido en un objeto de tipo Establecimiento
     */
    public Establecimiento(@NonNull QueryDocumentSnapshot dato) {
        this.IdDocument = dato.getId();
        this.nombre = dato.getString("nombre");
        this.direccion = dato.getString("direccion");
        try {
            this.telefono = Objects.requireNonNull(dato).get("telefono", Integer.class);
        } catch (Exception e) {
            this.telefono = Integer.parseInt(
                    Objects.requireNonNull(dato.get("telefono", String.class)));
        }
        this.propietario = dato.getString("propietario");

        try {
            this.imagen = Objects.requireNonNull(dato).get("imagen", Integer.class);
        } catch (Exception e) {
            this.imagen = 2131165315;
        }
    }
}
