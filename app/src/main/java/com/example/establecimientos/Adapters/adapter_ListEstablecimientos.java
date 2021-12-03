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
    public adapter_ListEstablecimientos(@NonNull Context context, int Resource, @NonNull List<Establecimiento> Establecimientos) {
        super(context, Resource, Establecimientos);
        this.establecimientoList = Establecimientos;
        this.context = context;
        this.resourceLayout = Resource;
    }

    // Métodos
    // - Crear la vista de cada Fila/Row
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View _view = convertView;
        if (_view == null)
            _view = LayoutInflater.from(context).inflate(resourceLayout, null);

        // Tomamos los datos del elemento clickeado
        Establecimiento sel_establecimiento = establecimientoList.get(position);

        // Cargamos en la View los datos del elemento de la fila
        ImageView imagen = _view.findViewById(R.id.imageView);
        // Cargar imagen
        imagen.setImageResource(sel_establecimiento.image);

        TextView
                textoNombre = _view.findViewById(R.id.txtNombre),
                textoDirecc = _view.findViewById(R.id.txtDireccion),
                textoPropie = _view.findViewById(R.id.txtPropietario),
                textoTelefo = _view.findViewById(R.id.txtTelefono);

        // Llevar los valores del establecimiento a su correspondiente elemento de la vista
        textoNombre.setText(sel_establecimiento.nombre);
        textoDirecc.setText(sel_establecimiento.direccion);
        textoPropie.setText(sel_establecimiento.propietario);
        textoTelefo.setText(sel_establecimiento.telefono);

        return _view;
    }
}
