package com.example.establecimientos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Nuevo extends AppCompatActivity {
    FirebaseFirestore BD=FirebaseFirestore.getInstance();
    TextView nom, pro, dir,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        nom=(TextView) findViewById(R.id.txtNom);
        pro=(TextView)  findViewById(R.id.txtPro);
        dir=(TextView) findViewById(R.id.txtDir);
        tel=(TextView) findViewById(R.id.txtTel);
    }

    public void Agregar (View v) {
        Map<String, Object> est= new HashMap<>();
        est.put("NomEmp", nom.getText().toString());
        est.put("NomPro", pro.getText().toString());
        est.put("DirEmp", dir.getText().toString());
        est.put("TelEmp", tel.getText().toString());

        BD.collection("Establecimientos")
                .add(est)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Activity4_Nuevo", "DocumentSnapshot added with ID: " + documentReference.getId());}})
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Activity4_Nuevo", "Error adding document", e);}});
        nom.setText("");
        pro.setText("");
        dir.setText("");
        tel.setText("");
        Context C = getApplicationContext();
        Toast Aviso = Toast.makeText(C,"Establecimiento Agregado",Toast.LENGTH_LONG);
        Aviso.show();
    }

    public void Volver (View v)  {
        Intent Vol = new Intent(this, Menu.class);
        startActivity(Vol);
    }
}