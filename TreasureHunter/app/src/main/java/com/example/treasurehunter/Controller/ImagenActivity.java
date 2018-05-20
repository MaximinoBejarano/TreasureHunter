package com.example.treasurehunter.Controller;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.treasurehunter.R;

import java.io.File;

public class ImagenActivity extends AppCompatActivity {
    private ImageView imv_img;
    protected Button btnCargarImg;
    protected Uri Direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        //Alambrado de componentes
        setContentView(R.layout.activity_imagen);
        btnCargarImg=findViewById(R.id.btnCargarImg);
        this.imv_img = (ImageView) findViewById(R.id.imgFoto);

        btnCargarImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Imagen = new Intent(getApplicationContext(),RompecabezasActivity.class);
                startActivity(Imagen);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

    }
    /**********************************************************************************************/
    public void btn_image_click_camara(View view) {
        Intent m_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File imagePath = new File(getFilesDir(), "images");
        imagePath.mkdir();
        File file = new File(imagePath, "profile.jpg");
        Uri uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);

        m_intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(m_intent, 0);
    }

    public void btn_image_click_galeria(View view) {
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
                    imv_img.invalidate();
                    imv_img.setImageURI(capturedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    Direccion=selectedImage;
                    imv_img.setImageURI(selectedImage);
                }
                break;
        }
    }

    }
