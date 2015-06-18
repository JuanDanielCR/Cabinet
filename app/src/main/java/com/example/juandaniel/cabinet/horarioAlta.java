
package com.example.juandaniel.cabinet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class horarioAlta extends ActionBarActivity {
    String horarios,id_receta;
    int timers;
    int num_total;
    long id_medicamento;
    LinearLayout layout;
    TextView prueba;
    TimePicker chooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_alta);
        setToolbar();

        Bundle recibo;
        recibo=this.getIntent().getExtras();
        id_receta=recibo.getString("id_receta");
        horarios=recibo.getString("num");
        id_medicamento=recibo.getLong("id_medicamento");

        //
        timers=Integer.parseInt(horarios);
        crearTimers(timers);
    }

    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Horarios");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
    public void crearTimers(int numeros){
       layout = (LinearLayout) findViewById(R.id.layouttime);
        for(int i=0; i<numeros; i++) {
            prueba= new TextView(this);
            prueba.setText("Horario número: "+i);
            chooser = new TimePicker(this);
            chooser.setId(i);

            layout.addView(chooser);
        }
    }
    public void AgregarHorarios(View view){
        int num=timers;
        int id_rec=Integer.parseInt(id_receta);

        SQL helper= new SQL(this,"CabinetDB",null,8);
        SQLiteDatabase db= helper.getWritableDatabase();
        SQLiteDatabase dbc= helper.getReadableDatabase();

        String[] campos = {"duracion"};

        Cursor selectAll = db.query("recetas", campos, "id_receta=?", new String[]{id_receta}, null, null, null);
        int aux;//variabe para traer solo el primer medicamento
        int Total = selectAll.getCount();
        if(Total==0){
            int o=0;
        }else {

            for (int i = 1; i <= Total; i++) {
                selectAll.moveToNext();
                num_total=selectAll.getInt(0);
                ////aqui hago otra consulta para mandra un array de medicamentos
            }
        }



        ContentValues relacion= new ContentValues();
        relacion.put("id_receta",id_rec);
        relacion.put("id_medicamento",id_medicamento);

        try{db.insert("receta_medicamento", null, relacion);}catch (Exception e){System.out.print(e.toString());}

        //Creacion del content value
        int tomada=0;
        int hora,minuto;
        ContentValues bus= new ContentValues();
        for(int j=0; j<num;j++){
        //Llenado del content value
            layout=(LinearLayout)findViewById(R.id.layouttime);
            chooser=(TimePicker)layout.getChildAt(j);
            hora=chooser.getCurrentHour();
            minuto=chooser.getCurrentMinute();
                bus.put("id_receta", id_receta);
                bus.put("id_medicamento", id_medicamento);
                bus.put("hora", hora);
                bus.put("minuto", minuto);
                bus.put("tomada", tomada);
            for(int u=0; u<num_total;u++){
                if(db != null){
                    try{db.insert("notificaciones", null, bus);}catch (Exception e){System.out.print(e.toString());}
                }else{System.out.print("error");}
            }

        //Limpiando el bus de datos que inserta
        bus.clear();
        }
        Toast.makeText(this, "Has agregado un medicamento con éxito!", Toast.LENGTH_LONG).show();
        Intent finish= new Intent(this,mis_recetas.class);
        startActivity(finish);
        finish();
    }
/*
* Estado=0, inactiva
* tomada=0, no tomada
* */
}
