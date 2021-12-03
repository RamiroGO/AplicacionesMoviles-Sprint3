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
    private final List<Establecimiento> establecimientoList;
    private final Context context;
    private final int resourceLayout;

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
        // Variables Locales
        String _telefonoString;
        View _view = convertView;
        Establecimiento sel_establecimiento;
        TextView
                textoNombre,
                textoDirecc,
                textoPropie,
                textoTelefo;

        if (_view == null)
            _view = LayoutInflater.from(context).inflate(resourceLayout, null);

        // Enlazar las variables con el elemento de la fila en la lista para la vista
        textoNombre = _view.findViewById(R.id.txtNombre);
        textoDirecc = _view.findViewById(R.id.txtDireccion);
        textoPropie = _view.findViewById(R.id.txtPropietario);
        textoTelefo = _view.findViewById(R.id.txtTelefono);

        // Tomamos los datos del elemento de la fila
        sel_establecimiento = establecimientoList.get(position);

        // Cargamos en la View los datos del elemento de la fila
        ImageView imagen = _view.findViewById(R.id.imageView);
        // Cargar imagen en la View a partir de su valor numérico
        imagen.setImageResource(sel_establecimiento.imagen);

        // Llevar los valores del establecimiento a su correspondiente elemento de la vista
        textoNombre.setText(sel_establecimiento.nombre);
        textoDirecc.setText(sel_establecimiento.direccion);
        textoPropie.setText(sel_establecimiento.propietario);

        // Convertir valor numérico para poder cargarlo en la View
        _telefonoString = String.valueOf(sel_establecimiento.telefono);
        // Cargar número en la View
        textoTelefo.setText(_telefonoString);

        return _view;
    }
}
