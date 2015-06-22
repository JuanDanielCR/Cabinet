package com.example.juandaniel.cabinet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

//Clase donde solo abra imagenes para poder decirle al usuario que hacer en la app, sera como un manual
public class CabinetTour extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet_tour);
        setToolbar();
    }
    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Mis Circulos");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
}
