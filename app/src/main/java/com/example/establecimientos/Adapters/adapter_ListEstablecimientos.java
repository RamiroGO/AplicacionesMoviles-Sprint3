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
        View _view = convertView;
        Establecimiento rowDraw_establecimiento;
        TextView
                textoNombre,
                textoDirecc,
                textoPropie,
                textoTelefo;
        ImageView _imagen;
        int _id_imagen;
        String _telefonoString;

        // Comprobación de valores Null
        if (_view == null)
            _view = LayoutInflater.from(context).inflate(resourceLayout, null);

        // Enlazar las variables con el elemento de la fila en la lista para la vista
        textoNombre = _view.findViewById(R.id.txtNombre);
        textoDirecc = _view.findViewById(R.id.txtDireccion);
        textoPropie = _view.findViewById(R.id.txtPropietario);
        textoTelefo = _view.findViewById(R.id.txtTelefono);

        // Tomamos los datos del elemento de la fila
        rowDraw_establecimiento = establecimientoList.get(position);

        // Cargamos en la View los datos del elemento de la fila
        _imagen = _view.findViewById(R.id.imageView);
        // Cargar imagen en la View a partir de su identificador numérico
        _id_imagen = Integer.valueOf(rowDraw_establecimiento.imagen);
        try{
            _imagen.setImageResource(_id_imagen);
        }catch (Exception e){
            _imagen.setImageResource(R.drawable.img_locales);
        }

        // Llevar los valores del establecimiento a su correspondiente elemento de la vista
        textoNombre.setText(rowDraw_establecimiento.nombre);
        textoDirecc.setText(rowDraw_establecimiento.direccion);
        textoPropie.setText(rowDraw_establecimiento.propietario);

        // Convertir valor numérico para poder cargarlo en la View
        _telefonoString = String.valueOf(rowDraw_establecimiento.telefono);
        // Cargar número en la View
        textoTelefo.setText(_telefonoString);

        return _view;
    }
}
