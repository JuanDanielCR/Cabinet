package com.example.juandaniel.cabinet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JuanDaniel on 21-01-2015.
 */
public class TabContacto extends Fragment {
    Context context;
    TextView datos2;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabcontactos,container,false);
        context=getActivity().getApplicationContext();
        datos2=(TextView) v.findViewById(R.id.datos2);

        SQL sql = new SQL(context,"CabinetDB", null, 1);
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
                datos2.append(selectAll.getInt(0) + " Nombre: " + selectAll.getString(1)+ selectAll.getString(2)+selectAll.getString(3)+
                        "\n Teléfono: "+selectAll.getInt(4)+"\n E-mail: "+selectAll.getString(5)+"\n Prioridad: "+selectAll.getInt(6)+"\n Estado: "+selectAll.getInt(7)+"\n");
            }
            db.close();
        }
        return v;
    }
}