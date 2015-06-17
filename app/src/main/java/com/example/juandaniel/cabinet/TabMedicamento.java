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
 * Created by Juan Daniel on 14/06/2015.
 */
public class TabMedicamento extends Fragment {
    Context context;
    TextView estado;
    ListView lv;
    String id_receta,id_medicamento;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.tabmedicamentos,container,false);
        estado=(TextView)v.findViewById(R.id.estadomed);
        context=getActivity().getApplicationContext();

        id_receta=getArguments().getString("receta");

        //Obtener el list
        lv=(ListView) v.findViewById(R.id.listmedica);
        //ArrayList
        ArrayList<receta_med> itemsCompras= obteneritems();
        //Adaptador
        adapterMedicamento adapter = new adapterMedicamento(context,itemsCompras);
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
                TextView user=(TextView)v.findViewById(R.id.id_enviaasocia);
                String id_medicamento=user.getText().toString();

                Intent intent=new Intent(context,medicaPerfil.class);
                Bundle datos2 = new Bundle();
                datos2.putString("id_receta",id_receta);
                datos2.putString("id_medicamento",id_medicamento);
                intent.putExtras(datos2);
                startActivity(intent);
            }
        });
        return v;
    }
    //Metodo para crear cada item desde la base
    public ArrayList<receta_med> obteneritems(){
        ArrayList<receta_med> contacts = new ArrayList<>();

        SQL sql = new SQL(context,"CabinetDB", null, 4);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_receta","id_medicamento"};

        Cursor selectAll = db.query("recetas_medicamentos", campos, "id_receta=?", new String[]{id_receta}, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            estado.setText("No tienes medicamentos agregados a esta receta");
        }else {
            estado.setText("Medicamentos Registrados: \n");
            for (int i = 1; i <= Total; i++) {
                selectAll.moveToNext();
                contacts.add(new receta_med(selectAll.getInt(0), selectAll.getInt(1)));
            }
            db.close();
        }
        return contacts;
    }
}
