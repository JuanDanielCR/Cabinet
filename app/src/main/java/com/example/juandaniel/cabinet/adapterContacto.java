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
public class adapterContacto extends BaseAdapter {

    protected Context context;
    protected ArrayList<contacto> parametros;

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

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.itemfamilia, null);
        }

        contacto item = parametros.get(position);

        int eda=item.getNumero();
        int prio=item.getPrioridad();
        int id=item.getId();
        String edads=String.valueOf(eda);
        String prios=String.valueOf(prio);
        String ids=String.valueOf(id);

        TextView nombre = (TextView) vi.findViewById(R.id.familianombre);
        nombre.setText(item.getNombre()+" "+item.getPaterno()+" "+item.getMaterno());

        TextView sexo= (TextView)vi.findViewById(R.id.sexy);
        sexo.setText("Correo: "+item.getCorreo());

        TextView edad= (TextView)vi.findViewById(R.id.family);

        edad.setText("Número: "+edads);


        TextView idss= (TextView)vi.findViewById(R.id.id_contact);
        idss.setText(ids);

        return vi;
    }
}
