package com.example.juandaniel.cabinet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

    }

    public void Iniciar(View v){
        Intent inicio= new Intent(this,home.class);
        startActivity(inicio);
    }

    public void Emergencia(View v){
        Intent inicio= new Intent(this,Emergencia.class);
        startActivity(inicio);
    }

    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        setSupportActionBar(toolbar);
    }
}
