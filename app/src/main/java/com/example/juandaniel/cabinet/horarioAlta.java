
package com.example.juandaniel.cabinet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;


public class horarioAlta extends ActionBarActivity {
    String horarios,id_receta;
    int timers;
    long id_medicamento;
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
        LinearLayout layout = (LinearLayout) findViewById(R.id.layouttime);
        for(int i=0; i<numeros; i++) {
            prueba= new TextView(this);
            prueba.setText("Horario número: "+i);
            chooser = new TimePicker(this);
            chooser.setId(i);

            layout.addView(prueba);
            layout.addView(chooser);
        }
    }
    public void agregarHorarios(View v, int num){
        SQL helper= new SQL(this,"CabinetDB",null,4);
        SQLiteDatabase db= helper.getWritableDatabase();

        ContentValues bus= new ContentValues();
        for(int j=0; j<=num;j++){

        }
    }

}
