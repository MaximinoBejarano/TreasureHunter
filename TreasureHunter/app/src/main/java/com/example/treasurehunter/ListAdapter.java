package com.example.treasurehunter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Busqueda> implements Filterable {
    CustomFilter filter;
    List<Busqueda> Datos;
    ArrayList<Busqueda> FilterDatos;

    private static class ViewHolder {

        TextView txv_nombre;
        TextView txv_nivel;
    }

    public ListAdapter(Context contexto, ArrayList<Busqueda>
            datos) {
       super(contexto, R.layout.list_item,datos);
       FilterDatos=datos;
       //agregarDatos(datos);
    }

    public void agregarDatos(ArrayList<Busqueda> filterDatos) {
        this.FilterDatos=new ArrayList<Busqueda>();
        for(Busqueda b:filterDatos){
            this.FilterDatos.add(b);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtener los datos del item para esa posición
        Busqueda modeloDatos = getItem(position);
        ListAdapter.ViewHolder viewHolder;
        // Verificar si el view existe para ser reusado, si no se infla un view
        if (convertView == null) {
            viewHolder = new ListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item , parent, false);
            viewHolder.txv_nombre = convertView.findViewById(R.id.txtNombreBus);
            viewHolder.txv_nivel = convertView.findViewById(R.id.txtPuntosMinimos);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapter.ViewHolder) convertView.getTag();
        }
        // Se asignan los datos correspondientes según modelo de datos, a cada view

        viewHolder.txv_nombre.setText(modeloDatos.getTitulo());
        viewHolder.txv_nivel.setText(String.valueOf(modeloDatos.getNivel()));
        // Retornar el view completo para mostrar en pantalla
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if(filter == null)
        {
            filter=new CustomFilter();

        }

        return filter;
    }


    //INNER CLASS
    class CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // TODO Auto-generated method stub

            FilterResults results=new FilterResults();

            if(constraint != null && constraint.length()>0)
            {
                //CONSTARINT TO UPPER
                constraint=constraint.toString().toUpperCase();

                ArrayList<Busqueda> filters=new ArrayList<Busqueda>();

                //get specific items
                for(int i=0;i<FilterDatos.size();i++)
                {
                    if(FilterDatos.get(i).getTitulo().toUpperCase().contains(constraint))
                    {
                        Busqueda p=new Busqueda();
                        p.setId(FilterDatos.get(i).getId());
                        p.setTitulo(FilterDatos.get(i).getTitulo());
                        p.setDescripcion(FilterDatos.get(i).getDescripcion());
                        p.setContraseña(FilterDatos.get(i).getContraseña());
                        p.setLongitud(FilterDatos.get(i).getLongitud());
                        p.setTerminada(FilterDatos.get(i).getTerminada());
                        p.setPuntos(FilterDatos.get(i).getPuntos());
                        filters.add(p);
                    }
                }

                results.count=filters.size();
                results.values=filters;

            }else
            {
                results.count=FilterDatos.size();
                results.values=FilterDatos;

            }

            return results;
        }


        @Override
       protected void publishResults(CharSequence constraint, FilterResults results) {
            // TODO Auto-generated method stub
            clear();
            Datos=(ArrayList<Busqueda>)results.values;
            addAll(Datos);
            notifyDataSetChanged();
        }

    }

}
