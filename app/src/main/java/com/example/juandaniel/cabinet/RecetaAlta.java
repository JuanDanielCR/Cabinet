package com.example.juandaniel.cabinet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RecetaAlta extends ActionBarActivity {
    EditText nombre, duracion;
    String name;
    int days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_alta);
        setToolbar();
        nombre= (EditText) findViewById(R.id.nombreReceta);
        duracion=(EditText) findViewById(R.id.duracionReceta);

    }
    public void AltaReceta(View v){
        SQL sql = new SQL(this,"CabinetDB", null,4);
        final SQLiteDatabase db = sql.getWritableDatabase();
        name=nombre.getText().toString();
        days=Integer.parseInt(duracion.getText().toString());

        ContentValues datos = new ContentValues();
        datos.put("nombre", name);
        datos.put("duracion",days);

        if(db != null){
            try{
                db.insert("recetas", null, datos);
                Toast.makeText(getApplicationContext(), name + ". \n Se ha agregado a tu Recetario!", Toast.LENGTH_LONG).show();
            }catch (Exception e){Toast.makeText(getApplicationContext(),"Error en Insert " + e,Toast.LENGTH_LONG).show();}
        }else{Toast.makeText(getApplicationContext(),"No existe la base de datos",Toast.LENGTH_LONG).show();}

        Intent n= new Intent(this,mis_recetas.class);
        startActivity(n);
        finish();
    }

    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Agregando mis recetas");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
 }