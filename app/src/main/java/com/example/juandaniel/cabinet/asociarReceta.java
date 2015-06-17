package com.example.juandaniel.cabinet;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Toast;

import java.util.ArrayList;


public class asociarReceta extends ActionBarActivity {
    Context context;
    TextView data;
    String id,id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asociar_receta);
        setToolbar();

        Bundle recibo;
        recibo=this.getIntent().getExtras();
        id=recibo.getString("id_receta");

        data=(TextView)findViewById(R.id.data);
        context=this;
        //ListView
        ListView lv = (ListView)findViewById(R.id.listasocia);

        ArrayList<usuario> itemsCompra = obtenerItems();

        adapterUser adapter = new adapterUser(this, itemsCompra);

        lv.setAdapter(adapter);
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3){
                TextView usuario=(TextView) v.findViewById(R.id.id_user);
                id_usuario=usuario.getText().toString();
                SQL sql = new SQL(context,"CabinetDB",null, 4);

                SQLiteDatabase db_valida=sql.getReadableDatabase();
                String[] campos = {"id_receta", "id_usuario", "estado"};

                Cursor selectAll = db_valida.query("recetas_catalogo", campos, "id_usuario=? and id_receta=?", new String[]{id_usuario,id}, null, null, null);
                int Total = selectAll.getCount();
                if(Total==0){
                    SQLiteDatabase db= sql.getWritableDatabase();
                    ContentValues agregar= new ContentValues();
                    agregar.put("id_usuario",id_usuario);
                    agregar.put("id_receta",id);
                    agregar.put("estado", 0);
                    if(db != null){
                        try{
                            db.insert("recetas_catalogo", null, agregar);
                            Toast.makeText(getApplicationContext(), "Tu receta tiene un nuevo usuario", Toast.LENGTH_LONG).show();
                        }catch (Exception e){Toast.makeText(getApplicationContext(),"Error en Insert " + e,Toast.LENGTH_LONG).show();}
                    }else{Toast.makeText(getApplicationContext(),"No existe la base de datos",Toast.LENGTH_LONG).show();}
                }else {
                    Toast.makeText(getApplicationContext(), "El usuario seleccionado ya estaba asociado", Toast.LENGTH_LONG).show();
                }

                        Intent n= new Intent(context,mis_recetas.class);
                        startActivity(n);
                        finish();
            }
        });
    }

    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Creación de Recetas");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
    private ArrayList<usuario> obtenerItems() {
        ArrayList<usuario> items = new ArrayList<usuario>();

        SQL sql = new SQL(context,"CabinetDB", null, 4);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_usuario","nombre", "paterno","materno","edad","sexo","estado"};

        Cursor selectAll = db.query("usuarios", campos, null, null, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            data.setText("No tienes usuarios registrados");
        }else {
            data.setText("Usuarios Registrados: \n");
            for (int i = 1; i <= Total; i++) {
                selectAll.moveToNext();
                if(selectAll.getInt(6)==1){
                    items.add(new usuario(selectAll.getString(1),selectAll.getString(2),selectAll.getString(3),selectAll.getInt(4),selectAll.getString(5),selectAll.getInt(0)));
                }
            }
            db.close();
        }

        return items;
    }
}