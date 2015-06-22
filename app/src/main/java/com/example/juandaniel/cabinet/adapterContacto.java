package com.example.juandaniel.cabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juan Daniel on 12/06/2015.
 */

//El adapter es un clase que sirve para llenar listas de contenido con alguna fuentes de datos, en este caso la fuente de datos
//es un consulta a la BD por eso extiende de Base Adapter
public class adapterContacto extends BaseAdapter {

    protected Context context;
    protected ArrayList<contacto> parametros;

    //Constructor que recibe el contexto para poder imprimir, y un arrarylist con los parametros con los que llenare la vista
    public  adapterContacto(Context context ,ArrayList<contacto> params){
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
        return parametros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
//Obteniendo la vista del listView que voy a llenar
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.itemfamilia, null);
        }
//Obteniendo cada parametro del arraylist
        contacto item = parametros.get(position);
//Guardando los datos en variables
        int eda=item.getNumero();
        int prio=item.getPrioridad();
        int id=item.getId();
        String edads=String.valueOf(eda);
        String prios=String.valueOf(prio);
        String ids=String.valueOf(id);
//Enviando los datos ncesarios a las vista para que se impriman
        TextView nombre = (TextView) vi.findViewById(R.id.familianombre);
        nombre.setText(item.getNombre()+" "+item.getPaterno()+" "+item.getMaterno());

        TextView sexo= (TextView)vi.findViewById(R.id.sexy);
        sexo.setText("Correo: "+item.getCorreo());

        TextView edad= (TextView)vi.findViewById(R.id.family);

        edad.setText("Número: "+edads);


        TextView idss= (TextView)vi.findViewById(R.id.id_contact);
        idss.setText(ids);
//Regresando una vista ya con datos
        return vi;
    }
}
