package com.example.establecimientos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.establecimientos.Models.Establecimiento;
import com.example.establecimientos.R;

import java.util.List;

public class adapter_ListEstablecimientos extends ArrayAdapter<Establecimiento> {
// Variables de Clase
    private List<Establecimiento> establecimientoList;
    private Context context;
    private int resourceLayout;
// Constructores
    public adapter_ListEstablecimientos(@NonNull Context context, int resource, @NonNull List<Establecimiento> Establecimientos) {
        super(context, resource, Establecimientos);
        this.establecimientoList=Establecimientos;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View _view = convertView;
        if(_view== null)
            _view= LayoutInflater.from(context).inflate(R.layout.establecimiento_row, null);

        // Tomamos los datos del elemento clickeado
        Establecimiento sel_establecimiento = establecimientoList.get(position);

        // Cargamos los datos del elemento clickeado a la vista
        TextView textoNombre = _view.findViewById(R.id.txtNombre);
        textoNombre.setText(sel_establecimiento.nombre);

        return _view;
    }
}
