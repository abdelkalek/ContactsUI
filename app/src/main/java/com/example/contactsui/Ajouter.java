package com.example.contactsui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ajouter extends AppCompatActivity {
Button bntAjouter , btnAnnuler;
TextView nom , phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        getSupportActionBar().hide();
        bntAjouter = findViewById(R.id.ModiferBtn);
        btnAnnuler = findViewById(R.id.Anullerbtn);
        nom = findViewById(R.id.txtnomMOD);
        phone = findViewById(R.id.txtphoneMOD);
        bntAjouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataBaseHandler db = new DataBaseHandler(Ajouter.this);
                db.addContact(new Contact(nom.getText().toString(), phone.getText().toString()));
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
}