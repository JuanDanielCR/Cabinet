package com.example.juandaniel.cabinet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MedicamentoAlta extends ActionBarActivity {

    TextView nombre, dosis, via,frecuencia;
    String name,dosiz, viaz,frec,id_receta;
    long id_medic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_alta);
        setToolbar();
        nombre=(TextView) findViewById(R.id.nombreMedicamento);
        dosis=(TextView) findViewById(R.id.dosisMedicamento);
        via=(TextView) findViewById(R.id.viaMedicamento);
        frecuencia=(TextView) findViewById(R.id.frecuenciaMedicamento);
        Bundle recibo;
        recibo=this.getIntent().getExtras();
        id_receta=recibo.getString("id_receta");
    }

    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Medicamentos");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
    public void AltaMedicamento(View v){
        SQL sql = new SQL(this,"CabinetDB", null,4);
        final SQLiteDatabase db = sql.getWritableDatabase();
        name=nombre.getText().toString();
        dosiz=dosis.getText().toString();
        viaz=via.getText().toString();
        frec=frecuencia.getText().toString();


        ContentValues datos = new ContentValues();
        datos.put("medicamento", name);
        datos.put("dosis",dosiz);
        datos.put("via",viaz);

        if(db != null){
            try{
                id_medic=db.insert("medicamentos", null, datos);
                Toast.makeText(getApplicationContext(),String.valueOf(id_medic), Toast.LENGTH_LONG).show();
            }catch (Exception e){Toast.makeText(getApplicationContext(),"Error en Insert " + e,Toast.LENGTH_LONG).show();}
        }else{Toast.makeText(getApplicationContext(),"No existe la base de datos",Toast.LENGTH_LONG).show();}

        Intent n= new Intent(this,horarioAlta.class);
        Bundle data= new Bundle();
        data.putString("num",frec);
        data.putString("id_receta",id_receta);
        data.putLong("id_medicamento",id_medic);
        n.putExtras(data);
        startActivity(n);
        finish();
    }
}
