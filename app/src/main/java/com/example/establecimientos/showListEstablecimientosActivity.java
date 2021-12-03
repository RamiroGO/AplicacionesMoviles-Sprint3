package com.example.establecimientos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.establecimientos.Adapters.adapter_ListEstablecimientos;
import com.example.establecimientos.Models.Establecimiento;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void btnNewEstablecientoClick(View view) {
        Intent gotoNewEstablecimiento= new Intent(this, newEstablecimientoActivity.class);
        startActivity(gotoNewEstablecimiento);
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
        gotoEditEstablecimiento.putExtra("idDocument_establecimiento",establecimientoList.get(position).getId());
        gotoEditEstablecimiento.putExtra("nombre_establecimiento",establecimientoList.get(position).nombre);
        gotoEditEstablecimiento.putExtra("direcc_establecimiento",establecimientoList.get(position).direccion);
        gotoEditEstablecimiento.putExtra("nombre_propietario",establecimientoList.get(position).propietario);
        gotoEditEstablecimiento.putExtra("telefono",establecimientoList.get(position).telefono);
        gotoEditEstablecimiento.putExtra("imagen",establecimientoList.get(position).imagen);
        startActivity(gotoEditEstablecimiento);
    }

    /**
     * Usar el Adaptador para visualizar la Lista de establecimientos
     * en su correspondiente ListView
     */
    private void showListView_Establecimientos() {
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
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Recibir datos de la base de datos
                        for (QueryDocumentSnapshot Dato : Objects.requireNonNull(task.getResult())) {
                            establecimientoList.add(new Establecimiento(Dato));
                        }
                        showListView_Establecimientos();
                    } else
                        Toast.makeText(
                                showListEstablecimientosActivity.this,
                                "Falla en recibir datos",
                                Toast.LENGTH_LONG).show();
                });
    }
}
