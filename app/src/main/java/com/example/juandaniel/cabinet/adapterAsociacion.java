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
 * Created by Juan Daniel on 14/06/2015.
 */
public class adapterAsociacion  extends BaseAdapter {
    protected Context context;
    protected ArrayList<receta_user> parametros;
    String id_receta;

    public  adapterAsociacion(Context context ,ArrayList<receta_user> params){
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
        return parametros.get(position).getId_receta();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.itemasociacion, null);
        }

        receta_user item = parametros.get(position);

        int id=item.getId_usuario();
        String id_s =String.valueOf(id);
        TextView nombre = (TextView) vi.findViewById(R.id.asocianombre);

            SQL sql = new SQL(context,"CabinetDB", null, 4);
            final SQLiteDatabase db = sql.getReadableDatabase();

            String[] campos = {"id_usuario", "nombre","paterno","materno"};

            Cursor selectAll = db.query("usuarios", campos, "id_usuario=?",new String[]{id_s}, null, null, null);

            int Total = selectAll.getCount();
            if(Total!=0) {
                for(int i=1; i<=Total; i++) {
                    selectAll.moveToNext();
                    nombre.setText(selectAll.getString(1)+" "+selectAll.getString(2)+" "+selectAll.getString(3));
                }
                db.close();
            }

        TextView ids= (TextView)vi.findViewById(R.id.id_enviaasocia);
        ids.setText(id_s);
        return vi;
    }
}
