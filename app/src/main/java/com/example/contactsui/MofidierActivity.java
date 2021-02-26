package com.example.contactsui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MofidierActivity extends AppCompatActivity {
TextView Nom , tel ;
Button ModiferBtn ,AnnulerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mofidier);
        getSupportActionBar().hide();
        Nom = findViewById(R.id.txtnomMO);
        tel = findViewById(R.id.txtphoneMOD1);
        ModiferBtn = findViewById(R.id.ModiferBtn);
        AnnulerBtn = findViewById(R.id.Anullerbtn);
        int ide = getIntent().getIntExtra("ID",0);
        String nom1 = getIntent().getStringExtra("nom");
        String telr = getIntent().getStringExtra("telRes");
        Nom.setText(nom1);
        tel.setText(telr);
        ModiferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Contact c = new Contact();
                    c.setId(ide);
                    c.setName(Nom.getText().toString());
                    c.setPhoneNumber(tel.getText().toString());
                    DataBaseHandler db = new DataBaseHandler(v.getContext());
                    int i =  db.updateContact(c);
                 //   Toast.makeText(v.getContext(),"iD ="+ide+"   modifier Form = "+i,Toast.LENGTH_SHORT).show();
                    Toast.makeText(v.getContext(),"Information Changé  ",Toast.LENGTH_SHORT).show();
                    openActivity();
                }catch(Exception e)
                {
                    Toast.makeText(v.getContext(),"Thabet ro7eky3aych 5ouya : "+e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });

        AnnulerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MofidierActivity.this," See you",Toast.LENGTH_SHORT).show();
                openActivity();

            }
        });
    }
    private void openActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}