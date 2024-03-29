package com.example.juandaniel.cabinet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juan Daniel on 16/06/2015.
 */
//El adapter es un clase que sirve para llenar listas de contenido con alguna fuentes de datos, en este caso la fuente de datos
//es un consulta a la BD por eso extiende de Base Adapter
public class adapterMedicamento extends BaseAdapter {

    protected Context context;
    protected ArrayList<receta_med> parametros;
    String id_receta;

    //Constructor que recibe el contexto para poder imprimir, y un arrarylist con los parametros con los que llenare la vista
    public  adapterMedicamento(Context context ,ArrayList<receta_med> params){
        this.context=context;
        this.parametros=params;
    }

    @Override
    public int getCount() {
        return parametros.size();
    }

    @Override
    public Object getItem(int position) {
        return parametros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return parametros.get(position).getid_medicamento();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
//Obteniendo la vista del listView que voy a llenar
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.itemmedicamentos, null);
        }
//Obteniendo cada parametro del arraylist
        receta_med item = parametros.get(position);
//Guardando los datos en variables
        int id=item.getid_medicamento();

        String id_medica =String.valueOf(id);
        TextView nombre = (TextView) vi.findViewById(R.id.asocianombre);
        TextView dosis = (TextView) vi.findViewById(R.id.dosismedica);

        SQL sql = new SQL(context,"CabinetDB", null, 8);
        final SQLiteDatabase db = sql.getReadableDatabase();

        String[] campos = {"id_medicamento", "medicamento","dosis","via"};
        Cursor selectAll = db.query("medicamentos", campos, "id_medicamento=?",new String[]{id_medica}, null, null, null);

        int Total = selectAll.getCount();
        if(Total!=0) {
            for(int i=1; i<=Total; i++) {
                selectAll.moveToNext();
//Enviando los datos ncesarios a las vista para que se impriman
                nombre.setText(selectAll.getString(1));
                dosis.setText(selectAll.getString(2));
            }
            db.close();
        }
//Enviando los datos ncesarios a las vista para que se impriman
        TextView ids= (TextView)vi.findViewById(R.id.id_enviaasocia);
        ids.setText(id_medica);
//Regresando una vista ya con datos
        return vi;
    }
}
