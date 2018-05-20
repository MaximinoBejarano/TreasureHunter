package com.example.treasurehunter.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.treasurehunter.Model.Logro;
import com.example.treasurehunter.R;

import java.util.ArrayList;

public class ListAdapterLogro extends ArrayAdapter<Logro> {

    private static class ViewHolder {
        TextView txv_titulo;
        TextView txv_descripcion;
        TextView txv_recompensa;
    }

    public ListAdapterLogro(Context contexto, ArrayList<Logro>
            datos) {
        super(contexto, R.layout.list_item_recompensa, datos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtener los datos del item para esa posición
        Logro modeloDatos = getItem(position);
        ViewHolder viewHolder;
        // Verificar si el view existe para ser reusado, si no se infla un view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_recompensa, parent, false);
            viewHolder.txv_titulo = convertView.findViewById(R.id.txtTituloRecompensa);
            viewHolder.txv_descripcion = convertView.findViewById(R.id.txtObjetivoRecompensa);
            viewHolder.txv_recompensa =convertView.findViewById(R.id.txtPuntosRecompensa);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Se asignan los datos correspondientes según modelo de datos, a cada view
        viewHolder.txv_titulo.setText(modeloDatos.getTitulo());
        viewHolder.txv_descripcion.setText(modeloDatos.getDescripcion());
        viewHolder.txv_recompensa.setText(String.valueOf(modeloDatos.getItem()));
        // Retornar el view completo para mostrar en pantalla
        return convertView;
    }
}