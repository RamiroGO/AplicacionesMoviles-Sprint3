package com.example.establecimientos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.establecimientos.Adapters.adapter_ListEstablecimientos;
import com.example.establecimientos.Models.Establecimiento;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class showListEstablecimientosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // Variables de Clase
    // - Base de datos de Firestores
    private FirebaseFirestore db_firestore;

    // - visualización de la lista
    private ListView establecimientos_ListView;
    private List<Establecimiento> establecimientoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_establecimientos);

        // Configuración de la base de datos
        // Inicializar base de Datos
        db_firestore = FirebaseFirestore.getInstance();

        // Inicializar Lista
        establecimientoList = new ArrayList<>();

        // Enlazar variable con la Interfaz de la interfaz
        establecimientos_ListView = findViewById(R.id.Lista_Establecimientos);
        // Evento de Click
        establecimientos_ListView.setOnItemClickListener(this);

        // Recibir datos de Firestore y Visualizarlos en la ListView
        getListEstablecimiento_FirestoreDatabase();
    }


    /**
     * Evento de presionar el elemento "Establecimiento" en el ListView
     * */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // Mostrar la numeración del elemento clickeado
        Toast.makeText(
                this,
                "Elemento Clickeado: " + position,
                Toast.LENGTH_SHORT).show();

        // Ir al Activity para editar el establecimiento seleccionado:
        Intent gotoEditEstablecimiento= new Intent(showListEstablecimientosActivity.this,editEstablecimientoActivity.class);

        // Cargar los valores del establecimiento
        // para enviarlos al otro activity y editar el establecimiento
        gotoEditEstablecimiento.putExtra("nombre_establecimiento",establecimientoList.get(position).nombre);
        gotoEditEstablecimiento.putExtra("direcc_establecimiento",establecimientoList.get(position).direccion);
        gotoEditEstablecimiento.putExtra("nombre_propietario",establecimientoList.get(position).propietario);
        gotoEditEstablecimiento.putExtra("telefono",establecimientoList.get(position).telefono);
        gotoEditEstablecimiento.putExtra("Id",establecimientoList.get(position).Id);
        gotoEditEstablecimiento.putExtra("image",establecimientoList.get(position).image);
        startActivity(gotoEditEstablecimiento);
    }

    /**
     * Usar el Adaptador para visualizar la Lista de establecimientos
     * en su correspondiente ListView
     */
    private void setEstablecimientos_ListView() {
        adapter_ListEstablecimientos adapter_listEstablecimientos = new adapter_ListEstablecimientos(
                showListEstablecimientosActivity.this,
                R.layout.establecimiento_row,
                establecimientoList);
        establecimientos_ListView.setAdapter(adapter_listEstablecimientos);
    }

    /**
     * Descargar la lista de establecimiento desde la Base de Datos de FireStore
     */
    private void getListEstablecimiento_FirestoreDatabase() {
        // Recibir datos de la base de datos
        db_firestore
                .collection("Establecimientos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Recibir datos de la base de datos

                            for (QueryDocumentSnapshot Dato : task.getResult()) {
                                establecimientoList.add(new Establecimiento(Dato));
                            }
                            setEstablecimientos_ListView();
                        } else
                            Toast.makeText(
                                    showListEstablecimientosActivity.this,
                                    "Falla en recibir datos",
                                    Toast.LENGTH_LONG).show();
                    }
                });
    }
}
