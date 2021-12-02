package com.example.establecimientos.Models;

import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Establecimiento {
    // Las variables deben declararse como públicas
    // porque además de sus valores, sus nombres se usarán como variables a enviar
    public String Id;
    public String nombre;
    public String direccion;
    public String telefono;
    public String propietario;
    public int image;

    public Establecimiento(String nombre, String direccion, String telefono, String propietario, int image) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.propietario = propietario;
        this.image = image;
    }

    public Establecimiento(QueryDocumentSnapshot dato) {
        this.Id = dato.getId();
        this.nombre = dato.getString("nombre");
        this.direccion = dato.getString("direccion");
        this.telefono = dato.getString("telefono");
        this.propietario = dato.getString("propietario");
        this.image= Integer.parseInt(dato.getString("imagen"));
    }
}
