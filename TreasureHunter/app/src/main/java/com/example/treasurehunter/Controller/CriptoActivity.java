package com.example.treasurehunter.Controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.treasurehunter.R;

public class CriptoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cripto);

        btn_nuevo_fragmento=(Button)findViewById(R.id.btnNuevoFragmento);
        btn_eliminar_fragmento=(Button)findViewById(R.id.bntEliminarFragmento);
        btn_respuesta_fragmento=(Button)findViewById(R.id.btnRespuestaFragmento);
        btn_editar_cripto=(Button)findViewById(R.id.btnEditarCripto);
        btn_guardar_cripto=(Button)findViewById(R.id.btnGuardarCripto);
        grl_fragmentos=(GridLayout) findViewById(R.id.grwFragmentos);

        btn_nuevo_fragmento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                txt_fragmento = new EditText(getApplicationContext());
                txt_fragmento.setTextColor(Color.BLACK);
                txt_fragmento.setHintTextColor(Color.BLACK);
                txt_fragmento.setHint("Fragmento");
                txt_fragmento.setTextSize(20);
                txt_fragmento.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                txt_fragmento.requestFocus();

                txt_signo= new TextView(getApplicationContext());
                txt_signo.setText("+");
                txt_signo.setTextSize(20);
                txt_signo.setTextColor(Color.BLACK);


                grl_fragmentos.addView(txt_fragmento);
                grl_fragmentos.addView(txt_signo);

            }
        });
        btn_eliminar_fragmento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(grl_fragmentos.getChildCount()>1) {
                    grl_fragmentos.removeViewAt(grl_fragmentos.getChildCount() - 1);
                    grl_fragmentos.removeViewAt(grl_fragmentos.getChildCount() - 1);
                }else if(grl_fragmentos.getChildCount()==1) {
                    grl_fragmentos.removeViewAt(grl_fragmentos.getChildCount() - 1);

                }
            }
        });
        btn_respuesta_fragmento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(grl_fragmentos.getChildCount()>1) {
                    grl_fragmentos.removeViewAt(grl_fragmentos.getChildCount() - 1);

                    txt_fragmento = new EditText(getApplicationContext());
                    txt_fragmento.setTextColor(Color.BLACK);
                    txt_fragmento.setHintTextColor(Color.BLACK);
                    txt_fragmento.setHint("Fragmento");
                    txt_fragmento.setTextSize(20);
                    txt_fragmento.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    txt_fragmento.requestFocus();

                    txt_signo= new TextView(getApplicationContext());
                    txt_signo.setText("=");
                    txt_signo.setTextSize(20);
                    txt_signo.setTextColor(Color.BLACK);

                    grl_fragmentos.addView(txt_signo);
                    grl_fragmentos.addView(txt_fragmento);

                }
            }
        });
    }
    Button btn_nuevo_fragmento;
    Button btn_eliminar_fragmento;
    Button btn_respuesta_fragmento;
    Button btn_editar_cripto;
    Button btn_guardar_cripto;
    GridLayout grl_fragmentos;
    EditText txt_fragmento;
    TextView txt_signo;
}
