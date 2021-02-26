package com.example.contactsui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Ajouter extends AppCompatActivity {
    public    int RESULT_LOAD_IMG;
ImageButton bntAjouter   ;
    ImageView photoprofil;
        Button btnAnnuler;
        TextInputEditText nom , phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        getSupportActionBar().hide();
        bntAjouter = findViewById(R.id.ModiferBtn);
        btnAnnuler = findViewById(R.id.Anullerbtn);
        nom = findViewById(R.id.txtnomMOD);
        phone =  findViewById(R.id.txtphoneMOD);
        photoprofil = findViewById(R.id.profileimage);
        photoprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });



        bntAjouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 /////a verifier ///////
                try{
                    ImageView imageView = (ImageView) findViewById(R.id.profileimage);
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageInByte = baos.toByteArray();


                    DataBaseHandler db = new DataBaseHandler(Ajouter.this);
                    db.addContact(new Contact(nom.getText().toString(), phone.getText().toString(),imageInByte ));
                }catch(Exception ex){
                    Toast.makeText(Ajouter.this,"class Ajouter EXception : "+ex,Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(Ajouter.this," Contact creé avec success",Toast.LENGTH_SHORT).show();
                 openActivity();

            }
        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Ajouter.this," See you",Toast.LENGTH_SHORT).show();
                openActivity();

            }
        });



    }




        private void openActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
             photoprofil.setImageBitmap(selectedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }


}