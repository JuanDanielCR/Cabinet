package com.example.juandaniel.cabinet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class RecetaHome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_home);
        setToolbar();
    }
    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitle("Recetas");
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }
    public void mias(View view){
        Intent n= new Intent(this, mis_recetas.class);
        startActivity(n);
    }
}
