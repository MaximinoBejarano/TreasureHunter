package com.example.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("");

        Button btn_jugar=(Button)findViewById(R.id.btnJugar);
        Button btn_diseñar=(Button)findViewById(R.id.btnDisenar);
        Button btn_logros=(Button)findViewById(R.id.btnLogros);
        Button btn_perfil=(Button)findViewById(R.id.btnPerfil);
        Button btn_ajustes=(Button)findViewById(R.id.btnAjustes);
        Button btn_acerca=(Button)findViewById(R.id.btnAcerca);
        Button btn_salir=(Button)findViewById(R.id.btnSalir);

        btn_jugar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Busqueda = new Intent(getApplicationContext(), BusquedaActivity.class);
                Busqueda.putExtra("bus_juego",true);
                startActivity(Busqueda);
            }
        });
        btn_diseñar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Busqueda = new Intent(getApplicationContext(), BusquedaActivity.class);
                Busqueda.putExtra("bus_juego",false);
                startActivity(Busqueda);
            }
        });
        btn_logros.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Imagen = new Intent(getApplicationContext(),ImagenActivity.class);
                startActivity(Imagen);
            }
        });
        btn_perfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Perfil = new Intent(getApplicationContext(), RegistrarActivity.class);
                startActivity(Perfil);
            }
        });
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Ajustes = new Intent(getApplicationContext(), AjustesActivity.class);
                startActivity(Ajustes);
            }
        });
        btn_acerca.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent AcercaDe = new Intent(getApplicationContext(), AcercaDeActivity.class);
                startActivity(AcercaDe);
            }
        });
        btn_salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

    }



}
