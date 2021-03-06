package com.example.contactsui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView1 =findViewById(R.id.RecycleViewContact);
        DataBaseHandler db = new DataBaseHandler(MainActivity.this);
        int nb = db.getContactsCount();
        try{
            List<Contact> lst = db.getAllContact();
            adapter = new ContactAdapter(this, lst);
        }catch(Exception ex){
            Toast.makeText(this,"class MainActivity EXception : "+ex,Toast.LENGTH_SHORT).show();

        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView1.setLayoutManager(gridLayoutManager);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openActivity();
            }
        });

    }

    private void openActivity() {
        Intent intent = new Intent(this, Ajouter.class);
        startActivity(intent);
    }



}