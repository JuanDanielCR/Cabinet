package com.example.juandaniel.cabinet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class mis_recetas extends ActionBarActivity {

    TextView id,nombre,duracion;
    TextView datos;
    String name,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_recetas);
        setToolbar();

        datos= (TextView)findViewById(R.id.datosrecetas);
        ListView v = (ListView)findViewById(R.id.listarecetas);

        ArrayList<receta> itemsCompras = obtenerItems();
        adapterRecetas adapterR = new adapterRecetas(this, itemsCompras);


        v.setAdapter(adapterR);
        v.setClickable(true);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3)
            {
                id=(TextView) v.findViewById(R.id.id_receta);
                nombre=(TextView) v.findViewById(R.id.recetanombre);
                duracion=(TextView) v.findViewById(R.id.duracion);

                name=nombre.getText().toString();
                time=duracion.getText().toString();

                String id_rec=id.getText().toString();
                Intent intent=new Intent(getApplicationContext(),recetaPerfil.class);
                Bundle envio = new Bundle();
                envio.putString("id_recetas",id_rec);
                envio.putString("nombre_receta",name);
                envio.putString("duracion_receta",time);
                intent.putExtras(envio);
                startActivity(intent);
                finish();
            }
        });

    }
    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Recetario");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
    public void AgregarReceta(View v){
        Intent inicio= new Intent(this ,RecetaAlta.class);
        startActivity(inicio);
        finish();
    }
    public ArrayList<receta> obtenerItems(){
        ArrayList<receta> items = new ArrayList<receta>();
        SQL sql = new SQL(this,"CabinetDB", null, 8);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_receta","nombre", "duracion"};

        Cursor selectAll = db.query("recetas", campos, null, null, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            datos.setText("No tienes recetas registrados");
        }else {
            datos.setText("Recetas Registradas: \n");
            for (int i = 1; i <= Total; i++) {
                selectAll.moveToNext();
                items.add(new receta(selectAll.getString(1),selectAll.getInt(2), selectAll.getInt(0)));
            }
            db.close();
        }
        return items;
    }
}
