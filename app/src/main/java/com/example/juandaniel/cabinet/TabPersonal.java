package com.example.juandaniel.cabinet;

/**
 * Created by Juan Daniel on 07/06/2015.
 */

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

public class TabPersonal extends Fragment {
    Context context;
    TextView datos;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tabpersonal,container,false);
        context=getActivity().getApplicationContext();
        datos=(TextView) v.findViewById(R.id.datos);

        SQL sql = new SQL(context,"CabinetDB", null, 1);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_usuario", "nombre", "paterno","materno","edad","sexo","estado"};

        Cursor selectAll = db.query("usuarios", campos, null, null, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            datos.setText("No tienes usuarios registrados");
        }else {
            datos.setText("Usuarios Registrados: \n");
            for (int i = 1; i <= Total; i++) {
                selectAll.moveToNext();
                datos.append(selectAll.getInt(0) + " Nombre: " + selectAll.getString(1)+ selectAll.getString(2)+selectAll.getString(3)+
                        "\n Edad: "+selectAll.getInt(4)+"\n Sexo: "+selectAll.getString(5)+"\n Estado: "+selectAll.getInt(6)+"\n");
            }
            db.close();
        }
        return v;
    }
}