package com.example.treasurehunter.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.treasurehunter.Model.Busqueda;
import com.example.treasurehunter.Model.Logro;
import com.example.treasurehunter.Model.Response;
import com.example.treasurehunter.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class LogrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros);
        ListView ltv_logros= (ListView) findViewById(R.id.ltvLogros);
        getItemsFromJson();
        adapter = new ListAdapterLogro(this,items );
        ltv_logros.setAdapter(adapter);

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

    public ArrayList<Logro> getItemsFromJson() {

        // en este metodo se toma el buffer cargado desde el .json y se convierte en el Gson, que son los objetos
        // tratados desde el buffer, ademas en este proceso participa la clase Respose, que se encarga de colocar cada
        // dato en su lugar.
        Response response = null;
        items = new ArrayList<>();
        Gson gson = new Gson();
        String json = readJson();

        try {

            response = gson.fromJson(json, Response.class);


                items = response.getDataL();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    ListView ltv_logros;
    ArrayList<Logro> items;
    ArrayAdapter<Logro> adapter;

}
