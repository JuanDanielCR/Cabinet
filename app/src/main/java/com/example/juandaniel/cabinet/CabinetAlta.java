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
import android.widget.Switch;
import android.widget.Toast;


public class CabinetAlta extends ActionBarActivity {
    String name;
    String paterno;
    String materno;
    int edad=0;
    String sexo;
    int estado=1;
    EditText nombre,papa,mama,años;
    Switch sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet_alta);
        setToolbar();
        nombre=(EditText)findViewById(R.id.txtName);
        papa=(EditText)findViewById(R.id.txtPaterno);
        mama=(EditText)findViewById(R.id.txtMaterno);
        años=(EditText)findViewById(R.id.txtAge);
        sex=(Switch)findViewById(R.id.sexo);
        sex.setTextOff("Masculino");
        sex.setTextOn("Femenino");

    }
    public void AgregarUsuarioDB(View view){

	    SQL sql = new SQL(this,"CabinetDB", null, 8);
	    final SQLiteDatabase db = sql.getWritableDatabase();
            name=nombre.getText().toString();
            paterno=papa.getText().toString();
            materno=mama.getText().toString();
            edad=Integer.parseInt(años.getText().toString());
            sexo="Masculino";
        ContentValues datos = new ContentValues();
		datos.put("nombre", name);
		datos.put("paterno", paterno);
		datos.put("materno", materno);
		datos.put("edad", edad);
        datos.put("sexo", sexo);
        datos.put("estado", estado);

		if(db != null){
			try{
				db.insert("usuarios", null, datos);
                Toast.makeText(getApplicationContext(),name+" "+paterno+" "+materno+". \n Bienvenid@ a Cabinet!",Toast.LENGTH_LONG).show();
			}catch (Exception e){Toast.makeText(getApplicationContext(),"Error en Insert " + e,Toast.LENGTH_LONG).show();}
		}else{Toast.makeText(getApplicationContext(),"No existe la base de datos",Toast.LENGTH_LONG).show();}
        Intent n= new Intent(this,home.class);
        startActivity(n);
        finish();
     }
    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Usuarios");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
}
