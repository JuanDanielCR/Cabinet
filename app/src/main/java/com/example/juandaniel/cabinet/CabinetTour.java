package com.example.juandaniel.cabinet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;


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
