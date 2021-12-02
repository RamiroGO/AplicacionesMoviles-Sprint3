package com.example.establecimientos.Models;

import android.widget.TextView;

import androidx.annotation.NonNull;

public class Usuario {
    // Las variables deben declararse como públicas
    // porque además de sus valores, sus nombres se usarán como variables a enviar
    public String nombre;
    public String contraseña;

    public Usuario(@NonNull TextView nombre, @NonNull TextView contraseña) {
        this.nombre = nombre.getText().toString();
        this.contraseña = contraseña.getText().toString();
    }
}
