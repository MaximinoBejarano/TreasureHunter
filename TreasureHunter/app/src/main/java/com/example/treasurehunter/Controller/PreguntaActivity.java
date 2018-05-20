package com.example.treasurehunter.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.treasurehunter.R;

public class PreguntaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        setTitle(R.string.title_activity_pregunta);
        btn_nueva_respuesta=(Button)findViewById(R.id.btnNuevaRespuesta);
        btn_editar_pregunta=(Button)findViewById(R.id.btnEditarPregunta);
        btn_guardar_pregunta=(Button)findViewById(R.id.btnGuardarPregunta);
        btn_eliminar_respuesta=(Button)findViewById(R.id.bntEliminarRespuesta);
        lnl_respuestas=(LinearLayout)findViewById(R.id.lnlRespuestas);
        txt_pregunta=(EditText)findViewById(R.id.txtPregunta);


        btn_nueva_respuesta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lnl_respuesta=  new LinearLayout(getApplicationContext());
                lnl_respuesta.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                lnl_respuesta.setOrientation(LinearLayout.HORIZONTAL);
                lnl_respuesta.setGravity(Gravity.CENTER);

                txt_respuesta = new EditText(getApplicationContext());
                txt_respuesta.setTextColor(Color.BLACK);
                txt_respuesta.setHintTextColor(Color.BLACK);
                txt_respuesta.setHint("Ingrese la respuesta");
                txt_respuesta.setLayoutParams(new LinearLayout.LayoutParams(500, TableLayout.LayoutParams.WRAP_CONTENT));
                txt_respuesta.requestFocus();

                ckb_correcta= new CheckBox(getApplicationContext());
                ckb_correcta.setText("Correcta");
                ckb_correcta.setSelected(false);
                ckb_correcta.setTextColor(Color.BLACK);
                lnl_respuesta.addView(txt_respuesta);
                lnl_respuesta.addView(ckb_correcta);
                lnl_respuestas.addView(lnl_respuesta);

            }
        });
        btn_eliminar_respuesta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(lnl_respuestas.getChildCount()>0) {
                    lnl_respuestas.removeViewAt(lnl_respuestas.getChildCount() - 1);
                }
            }
        });
    }


    Button btn_nueva_respuesta;
    Button btn_eliminar_respuesta;
    Button btn_guardar_pregunta;
    Button btn_editar_pregunta;
    LinearLayout lnl_respuestas;
    LinearLayout lnl_respuesta;
    EditText txt_pregunta;
    EditText txt_respuesta;
    CheckBox ckb_correcta;
}
