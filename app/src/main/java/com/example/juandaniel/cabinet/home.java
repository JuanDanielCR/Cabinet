package com.example.juandaniel.cabinet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolbar();
    }
    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Inicio");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
    public void Perfil(View view){
        Intent personas= new Intent(this,CabientProfile.class);
        startActivity(personas);
    }
    public void Receta(View view){
        Intent personas= new Intent(this,RecetaHome.class);
        startActivity(personas);
    }
    public void Sac(View view){
        Intent personas= new Intent(this,SacHome.class);
        startActivity(personas);
    }
    public void Ayuda(View view){
        Intent personas= new Intent(this,Emergencia.class);
        startActivity(personas);
    }
}
