package com.example.juandaniel.cabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*** Created by Juan Daniel on 13/06/2015.
 */
public class adapterRecetas extends BaseAdapter {

    protected Context context;
    protected ArrayList<receta> parametros;


    public adapterRecetas(Context my_activiy, ArrayList<receta> params){
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

        View vi=convertView;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.itemreceta, null);
        }

        receta item = parametros.get(position);

        TextView nombre = (TextView) vi.findViewById(R.id.recetanombre);
        nombre.setText(item.getNombre());

        TextView sexo= (TextView)vi.findViewById(R.id.duracion);
        sexo.setText("Duración: "+item.getDuracion()+" días");


        int ids=item.getId();
        String d_user=String.valueOf(ids);
        TextView id= (TextView)vi.findViewById(R.id.id_receta);
        id.setText(d_user);

        return vi;
    }
}
