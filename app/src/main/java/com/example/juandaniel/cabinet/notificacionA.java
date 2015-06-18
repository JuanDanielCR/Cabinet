package com.example.juandaniel.cabinet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class notificacionA extends ActionBarActivity {

    String id_receta, id_medicamento, id_notificacion;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);
        Bundle recibo;
        recibo = this.getIntent().getExtras();
        id_receta = recibo.getString("id_receta");
        id_medicamento = recibo.getString("id_medicamento");
        id_notificacion= recibo.getString("id_notificacion");
        id = (TextView) findViewById(R.id.tomada_recibo);

        SQL sql = new SQL(this,"CabinetDB", null, 8);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] data_medicamento = {"medicamento","dosis", "via"};

        Cursor selectAll = db.query("medicamentos", data_medicamento,"id_medicamento=?", new String[] { id_medicamento }, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            id.setText("El medicamento ya no existe");
        }else {
            while(selectAll.moveToNext()){
               id.append("Nombre del medicamento: "+selectAll.getString(0)+"\n Dosis: "+selectAll.getString(1)+"\n Vía: "+selectAll.getString(2));
            }
        }
        db.close();
    }

    public void MedicamentoTomado(View view) {
        SQL auxiliar = new SQL(this, "CabinetDB", null, 8);
        final SQLiteDatabase db = auxiliar.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put("tomada", 1);

        if (db != null) {
            try {
                db.update("notificaciones", datos, "id_notificacion=?", new String[]{id_notificacion});
                Toast.makeText(this, "Medicamento tomado!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Cabinet: 'No se pudieron actualizar los datos ' " + e, Toast.LENGTH_LONG).show();
            }
        }
        Intent inte = new Intent(this, CabientProfile.class);
        startActivity(inte);
        finish();
    }
}

/*
* Aqui se hace una consulta a la bd, si ya no hay resgistros con tomado 0 el pending intent lo borramos
* */
