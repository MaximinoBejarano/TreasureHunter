package com.example.treasurehunter.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.treasurehunter.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.tit_login);
        btn_entrar=(Button)findViewById(R.id.btnEntrarLogin);
        btn_registrar=(Button)findViewById(R.id.btnRegistrarLogin);
        txt_contrasena=(EditText) findViewById(R.id.txtContrasenaLogin);
        txt_usuario=(EditText) findViewById(R.id.txtUsuarioLogin);
        btn_entrar.requestFocus();
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Registrar = new Intent(getApplicationContext(), RegistrarActivity.class);
                startActivity(Registrar);
            }
        });
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Registrar = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(Registrar);
            }
        });
    }


    Button btn_registrar;
    Button btn_entrar;
    EditText txt_usuario;
    EditText txt_contrasena;
}

