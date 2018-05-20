package com.example.treasurehunter.Controller;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.treasurehunter.R;

import java.io.File;

public class RegistrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        setTitle(R.string.tit_registrar);
        spinner = (Spinner) findViewById(R.id.spnPaises);
        imvFoto=(ImageView) findViewById(R.id.imvPostal);
        btn_Cargar=findViewById(R.id.btnCargarP);
        btn_Galeria=findViewById(R.id.btnCamaraP);


        String[] letra = {"Costa Rica","Estados Unidos","Mexico","España","China"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                Toast.makeText(adapterView.getContext(),
                        (String) adapterView.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });
    }
    /**********************************************************************************************/
    public void btnimage_click_camara(View view) {
        Intent m_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File imagePath = new File(getFilesDir(), "images");
        imagePath.mkdir();
        File file = new File(imagePath, "profile.jpg");
        Uri uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);

        m_intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(m_intent, 0);
    }

    public void btnimage_click_galeria(View view) {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }

    /**********************************************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    File imagePath = new File(getFilesDir(), "images");
                    File file = new File(imagePath, "profile.jpg");
                    Uri capturedImage = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);
                    Direccion=capturedImage;
                    imvFoto.invalidate();
                    imvFoto.setImageURI(capturedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    Direccion=selectedImage;
                    imvFoto.setImageURI(selectedImage);
                }
                break;
        }
    }

    protected Uri Direccion;
    ImageView imvFoto;
    Button btn_Cargar,btn_Galeria;
    Spinner spinner;
}
