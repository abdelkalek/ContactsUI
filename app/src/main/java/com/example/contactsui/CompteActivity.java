package com.example.contactsui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CompteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_compte);
    }
}