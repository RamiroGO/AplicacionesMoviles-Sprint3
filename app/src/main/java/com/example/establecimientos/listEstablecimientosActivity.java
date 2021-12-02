package com.example.establecimientos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class listEstablecimientosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // Variables de Clase
    // - Base de datos de Firestores
    private FirebaseFirestore db_firestore;

    // - visualización de la lista
    private ListView establecimientos_ListView;
    private List<Establecimiento> establecimientoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_establecimientos);

        // Enlazar variable con la Interfaz de la interfaz
        establecimientos_ListView = findViewById(R.id.Lista_Establecimientos);

        // Inicializar Lista
        establecimientoList = new ArrayList<>();

        // Evento de Click
        establecimientos_ListView.setOnItemClickListener(this);


        // Insertar datos a la lista
        establecimientoList.add(new Establecimiento(
                "Efecty",
                "Casa No 1",
                "444444",
                "Jaime",
                1));

        // Configuración de la base de datos
        // Inicializar base de Datos
        db_firestore = FirebaseFirestore.getInstance();
        // Recibir datos de la base de datos
        db_firestore
            .collection("Establecimientos")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        // Recibir datos de la base de datos
                        for (QueryDocumentSnapshot Dato:task.getResult()) {
                            establecimientoList.add(new Establecimiento(Dato));
                        }

                        // Usar el Adaptador para visualizar la Lista de establecimientos
                        // en su correspondiente ListView
                        establecimientos_ListView.setAdapter(
                                new adapter_ListEstablecimientos(
                                        listEstablecimientosActivity.this,
                                        R.layout.establecimiento_row,
                                        establecimientoList));
                    }
                    else
                        Toast.makeText(
                                listEstablecimientosActivity.this,
                                "Falla en recibir datos",
                                Toast.LENGTH_LONG).show();
                }
            });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(
                this,
                "Elemento Clickado: " + position,
                Toast.LENGTH_LONG).show();
    }
}