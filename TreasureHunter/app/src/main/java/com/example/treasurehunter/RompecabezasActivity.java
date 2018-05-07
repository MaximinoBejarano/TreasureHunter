package com.example.treasurehunter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.treasurehunter.Controller.ControllerPiezas;
import com.example.treasurehunter.Controller.TouchListener;
import com.example.treasurehunter.Model.Pieza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RompecabezasActivity extends AppCompatActivity {
    ArrayList<Pieza> piezas;
    int numColumnas=3;
    int numFilas=4;
    String Valor;
    ImageView imvPrincipal;
    RelativeLayout layout;
    ControllerPiezas cPiezas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rompecabezas);

        //Alambrado de componentes
        layout = findViewById(R.id.layout);
        imvPrincipal = findViewById(R.id.imvPrincipal);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        /*
        Valor= intent.getStringExtra("VALOR");
        numColumnas=Integer.parseInt( intent.getStringExtra("Columnas"));
        numFilas=Integer.parseInt( intent.getStringExtra("Filas"));
        imvPrincipal.setImageURI(Uri.parse(Valor));*/
        imvPrincipal.setImageResource(R.drawable.water);

        imvPrincipal.setVisibility(View.INVISIBLE);

        //Toast.makeText(getApplicationContext(),"Col "+ numColumnas+" Fill "+ numFilas, Toast.LENGTH_LONG).show();
        cPiezas=new ControllerPiezas(imvPrincipal,getApplicationContext(),numColumnas,numFilas);
        imvPrincipal.post(new Runnable() {
            @Override
            public void run() {
                piezas=cPiezas.DividirImagen();
                TouchListener touchListener = new TouchListener(RompecabezasActivity.this);
                // se barajan las piesas
                Collections.shuffle(piezas);
                for (Pieza piece : piezas) {
                    piece.setOnTouchListener(touchListener);
                    layout.addView(piece);
                    // Se colocan las piesas de forma aleatoria en la parte inferior de la pantalla
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) piece.getLayoutParams();
                    lParams.leftMargin = new Random().nextInt(layout.getWidth() - piece.piezaAncho);
                    lParams.topMargin = layout.getHeight() - piece.piezaAlto;
                    piece.setLayoutParams(lParams);
                }

            }
        });

    }

    public void checkGameOver() {
        if (isGameOver()) {
            finish();
        }
    }
    private boolean isGameOver() {
        for (Pieza piece : piezas) {
            if (piece.puedeMover) {
                return false;
            }
        }

        return true;
    }
}
