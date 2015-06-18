package com.example.juandaniel.cabinet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class medicaPerfil extends ActionBarActivity {
    String id_medicamento,id_receta;
    TextView estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medica_perfil);
        Bundle recibo;
        recibo = this.getIntent().getExtras();
        id_medicamento = recibo.getString("id_medicamento");
        id_receta = recibo.getString("id_receta");
        estado = (TextView) findViewById(R.id.estado);
        SQL sql = new SQL(this, "CabinetDB", null, 8);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"hora", "minuto"};

        Cursor selectAll = db.query("notificaciones", campos, "id_medicamento=?", new String[]{id_medicamento}, null, null, null);
        int aux;//variabe para traer solo el primer medicamento
        int Total = selectAll.getCount();
        if (Total == 0) {
            estado.setText("No tienes medicamentos agregados a esta receta");
        } else {
            estado.setText("Medicamentos Registrados: \n");
            for (int i = 1; i <= Total; i++) {
                selectAll.moveToNext();
                String hora, min;
                hora = String.valueOf(selectAll.getInt(0));
                min = String.valueOf(selectAll.getInt(1));
                estado.append("Horario: " + hora + " " + min + "\n");
                ////aqui hago otra consulta para mandra un array de medicamentos
            }
            db.close();

        }
    }
}
