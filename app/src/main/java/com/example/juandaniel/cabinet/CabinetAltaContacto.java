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
import android.widget.RatingBar;
import android.widget.Toast;


public class CabinetAltaContacto extends ActionBarActivity {

    String name;
    String paterno;
    String materno;
    long telefono=0;
    int estado=1;
    String correo;
    int prioridad=0;
    EditText nombre,papa,mama,phone,mail;
    RatingBar priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet_alta_contacto);
        nombre=(EditText)findViewById(R.id.txtName);
        papa=(EditText)findViewById(R.id.txtPaterno);
        mama=(EditText)findViewById(R.id.txtMaterno);
        phone=(EditText)findViewById(R.id.Telefono);
       mail=(EditText)findViewById(R.id.Correo);
        priority=(RatingBar)findViewById(R.id.Prioridad);
        setToolbar();
    }

    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Contactos");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
    public void AgregarContactoDB(View view){
        SQL sql = new SQL(this,"CabinetDB", null, 4);
        final SQLiteDatabase db = sql.getWritableDatabase();
        name=nombre.getText().toString();
        paterno=papa.getText().toString();
        materno=mama.getText().toString();
        telefono=Long.parseLong(phone.getText().toString());
        correo=mail.getText().toString();
        prioridad= (int) priority.getRating();

        ContentValues datos = new ContentValues();
        datos.put("nombre", name);
        datos.put("paterno", paterno);
        datos.put("materno", materno);
        datos.put("numero", telefono);
        datos.put("correo", correo);
        datos.put("prioridad", prioridad);
        datos.put("estado", estado);

        if(db != null){
            try{
                db.insert("contactos", null, datos);
                Toast.makeText(getApplicationContext(), name + " " + paterno + " " + materno + ". \n Es tu nuevo Contacto!", Toast.LENGTH_LONG).show();
            }catch (Exception e){Toast.makeText(getApplicationContext(),"Error en Insert " + e,Toast.LENGTH_LONG).show();}
        }else{Toast.makeText(getApplicationContext(),"No existe la base de datos",Toast.LENGTH_LONG).show();}
        Intent n= new Intent(this,home.class);
        startActivity(n);
        finish();
    }

}
