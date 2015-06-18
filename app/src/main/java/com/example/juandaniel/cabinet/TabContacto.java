package com.example.juandaniel.cabinet;

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

/**
 * Created by JuanDaniel on 21-01-2015.
 */
public class TabContacto extends Fragment {
    Context context;
    TextView datos2;
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabcontactos,container,false);
        context=getActivity().getApplicationContext();
        datos2=(TextView) v.findViewById(R.id.datos2);

        //Obtener el list
        lv=(ListView) v.findViewById(R.id.lista);
        //ArrayList
        ArrayList<contacto> itemsCompras= obteneritems();
        //Adaptador
        adapterContacto adapter = new adapterContacto(context,itemsCompras);
        //Mandando el dapater al listview
        lv.setAdapter(adapter);
        //Listener para darle ciclk
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {

                Intent intent=new Intent(context,contactoPerfil.class);
                Bundle datos2 = new Bundle();

                String name;
                TextView nombre;
                         nombre =(TextView)v.findViewById(R.id.id_contact);
                name=nombre.getText().toString();
                datos2.putString("name",name);
                intent.putExtras(datos2);
                startActivity(intent);
            }
        });
        return v;
    }

    //Metodo para crear cada item desde la base
    public ArrayList<contacto> obteneritems(){
        ArrayList<contacto> contacts = new ArrayList<>();

        SQL sql = new SQL(context,"CabinetDB", null, 8);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_contacto", "nombre", "paterno","materno","numero","correo","prioridad","estado"};

        Cursor selectAll = db.query("contactos", campos, null, null, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            datos2.setText("No tienes contactos registrados");
        }else {
            datos2.setText("Contactos Registrados: \n");
            for (int i = 1; i <= Total; i++) {
                selectAll.moveToNext();
                if(selectAll.getInt(7)==1) {
                    contacts.add(new contacto(selectAll.getString(1), selectAll.getString(2), selectAll.getString(3), selectAll.getString(5), selectAll.getInt(4), selectAll.getInt(6),selectAll.getInt(0)));
                }
            }
            db.close();
        }

        return contacts;
    }
}