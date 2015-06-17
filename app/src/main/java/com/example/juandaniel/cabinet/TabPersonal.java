package com.example.juandaniel.cabinet;

/**
 * Created by Juan Daniel on 07/06/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TabPersonal extends Fragment {
    Context context;
    TextView datos;

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tabpersonal,container,false);

        context=getActivity().getApplicationContext();
        datos= (TextView)v.findViewById(R.id.datos);
        //ListView
        ListView lv = (ListView)v.findViewById(R.id.lista);

        ArrayList<usuario> itemsCompra = obtenerItems();

        adapterUser adapter = new adapterUser(context, itemsCompra);

        lv.setAdapter(adapter);
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Intent intent=new Intent(context,usuarioPerfil.class);
                Bundle datos = new Bundle();

                String name;
                TextView nombre;
                         nombre =(TextView)v.findViewById(R.id.id_user);
                name=nombre.getText().toString();
                datos.putString("name",name);
                intent.putExtras(datos);
                startActivity(intent);
            }
        });
        return v;
    }
    private ArrayList<usuario> obtenerItems() {
        ArrayList<usuario> items = new ArrayList<usuario>();

        SQL sql = new SQL(context,"CabinetDB", null, 4);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_usuario","nombre", "paterno","materno","edad","sexo","estado"};

        Cursor selectAll = db.query("usuarios", campos, null, null, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            datos.setText("No tienes usuarios registrados");
        }else {
            datos.setText("Usuarios Registrados: \n");
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

