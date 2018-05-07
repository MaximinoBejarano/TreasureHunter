package com.example.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BusquedaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        setTitle("Buscar Partida");
        lnl_botones=(LinearLayout)findViewById(R.id.lnlOpciones);
        Intent intent = getIntent();
        if(((boolean)intent.getExtras().get("bus_juego")) ){
            lnl_botones.setVisibility(View.INVISIBLE);
        }else{
            lnl_botones.setVisibility(View.VISIBLE);
        }

        tgl_tipo=(Switch)findViewById(R.id.tglTipo);
        ltv_Lista = (ListView) findViewById(R.id.list_view);

        txt_nombre_busqueda = (SearchView) findViewById(R.id.txtNombreBusqueda);
        items_seleccionados=new ArrayList<>();
        getItemsFromJson();
        filtrarTipo(true);
        adapter = new  ListAdapter(this,items_seleccionados );
        ltv_Lista.setAdapter(adapter);

        txt_nombre_busqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && newText.length()>0) {
                    adapter.getFilter().filter(newText);
                }else{
                    filtrarTipo(tgl_tipo.isChecked());
                }
                return false;
            }
        });

      ltv_Lista.setTextFilterEnabled(true);

       tgl_tipo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               filtrarTipo(isChecked);
                adapter.notifyDataSetChanged();
            }
        });


    }

    public void filtrarTipo(boolean isChecked){
        if(isChecked){
            tgl_tipo.setText(tgl_tipo.getTextOn());
            items_seleccionados.clear();
            for(int i=0; i<items.size();i++){
                if(items.get(i).getContraseña().equals("")){
                    items_seleccionados.add(items.get(i));
                }
            }

        }else{
            tgl_tipo.setText(tgl_tipo.getTextOff());
            items_seleccionados.clear();
            for(int i=0; i<items.size();i++){
                if(!items.get(i).getContraseña().equals("")){
                    items_seleccionados.add(items.get(i));
                }
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ltv_Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {

                Intent Diseño = new Intent(getApplicationContext(), MapaDisActivity.class);
                Diseño.putExtra("busqueda",items_seleccionados.get(position));
                startActivity(Diseño);

            }

        });
    }


    private String readJson() {

        // se carga el texto que hay dentro del .json para poder tratarlo
        // en archivo del .json se encuentra en el directorio del proyecto en la carperta raw
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.datos_prueba)));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public ArrayList<Busqueda> getItemsFromJson() {


        Response response = null;
        items = new ArrayList<>();
        Gson gson = new Gson();
        String json = readJson();

        try {

            response = gson.fromJson(json, Response.class);
            items = response.getDataB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    ArrayList<Busqueda> items;
    ArrayList<Busqueda> items_seleccionados;
    ListView ltv_Lista;
    ArrayAdapter<Busqueda> adapter;
    SearchView txt_nombre_busqueda;
    TextView txv_puntos_minimos;
    Switch tgl_tipo;
    LinearLayout lnl_botones;
}
