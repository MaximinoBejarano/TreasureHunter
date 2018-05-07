package com.example.treasurehunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.tit_login);

    }

    public void EntrarClick(View view) {
        Intent Registrar = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(Registrar);    }

    public void RegistrarCilck(View view) {
        Intent Registrar = new Intent(getApplicationContext(), RegistrarActivity.class);
        startActivity(Registrar);
    }
}
