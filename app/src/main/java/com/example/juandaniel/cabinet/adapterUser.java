package com.example.juandaniel.cabinet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juan Daniel on 10/06/2015.
 */


//El adapter es un clase que sirve para llenar listas de contenido con alguna fuentes de datos, en este caso la fuente de datos
//es un consulta a la BD por eso extiende de Base Adapter
public class adapterUser extends BaseAdapter {

    protected Context context;
    protected ArrayList<usuario> parametros;
    Activity act;
    View vi;
    //Constructor que recibe el contexto para poder imprimir, y un arrarylist con los parametros con los que llenare la vista
    public adapterUser(Context my_activiy, ArrayList<usuario> params){
        this.context=my_activiy;
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
        return parametros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//Obteniendo la vista del listView que voy a llenar
        vi=convertView;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.itemfamilia, null);
        }
//Obteniendo cada parametro del arraylist
        usuario item = parametros.get(position);
//Enviando los datos ncesarios a las vista para que se impriman
        TextView nombre = (TextView) vi.findViewById(R.id.familianombre);
        nombre.setText(item.getNombre()+" "+item.getPaterno()+" "+item.getMaterno());

        TextView sexo= (TextView)vi.findViewById(R.id.sexy);
        sexo.setText("Sexo: "+item.getSexo());

        TextView edad= (TextView)vi.findViewById(R.id.family);
        int eda=item.getEdad();
        String edads=String.valueOf(eda);
        edad.setText("Edad: "+edads+" años");

        int ids=item.getId();
        String d_user=String.valueOf(ids);
        TextView id= (TextView)vi.findViewById(R.id.id_user);
        id.setText(d_user);
//Regresando una vista ya con datos
        return vi;
    }
}
