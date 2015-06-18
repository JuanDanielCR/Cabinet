package com.example.juandaniel.cabinet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class activacionReceta extends ActionBarActivity {
    TextView datos2,prueba;
    int activacion;
    long id_pendiente=0;
    String id_receta,id_usuario;
    Button activo;
    Calendar calendar;
    private int pendiente=1;
    private static int id_btn=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activacion_receta);
        datos2=(TextView)findViewById(R.id.activacion);
        prueba=(TextView)findViewById(R.id.prueba);
        setToolbar();
        Bundle recibo;
        recibo=this.getIntent().getExtras();

        id_receta=recibo.getString("id_receta");
        id_usuario=recibo.getString("id_usuario");

        activacion=activacion();
        if(activacion==1){
            datos2.setText("Ahora puedes activar tu receta. \n");
            LinearLayout layout = (LinearLayout) findViewById(R.id.buttondinamic);
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText("Activar");
            btnTag.setId(1 * 2);
            layout.addView(btnTag);
            activo=(Button) findViewById(1*2);
            activo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activar();
                }
            });
        }else{
            datos2.setText("No tienes medicamentos agregados a esta receta, esta acción es necesaria para activarla!");
        }
    }


    public int activacion(){
        activacion=0;
        SQL sql = new SQL(this,"CabinetDB", null, 8);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_receta","id_medicamento"};

        Cursor selectAll = db.query("notificaciones", campos, "id_receta=?", new String[]{id_receta}, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            activacion=0;
        }else {
            activacion=1;
            db.close();
        }
        return activacion;
    }

    public void desvincular(View view){
        SQL SQlite= new SQL(this,"CabinetDB",null,8);
        final SQLiteDatabase db= SQlite.getWritableDatabase();

        if(db != null) {
            try {
                db.delete("recetas_catalogo", "id_usuario=? and id_receta=?", new String[]{id_usuario,id_receta});
                Toast.makeText(this, "El paciente ya no esta asociado a la receta!", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        Intent inte= new Intent(this,mis_recetas.class);
        startActivity(inte);
    }

    public void activar(){
        SQL auxiliar= new SQL(this,"CabinetDB",null,8);
        final SQLiteDatabase dba=auxiliar.getWritableDatabase();
        final SQLiteDatabase dbc=auxiliar.getReadableDatabase();
        /*
        * 1-Obtener id_medicamento de id_receta.
        * 2-Obtener horario, tavla notificacion
        * 3-Hacer un intent con la union id_receta-medicamento(nombre), nombre_usuario como nombre----para poder apagar la alarma quietar el pending intent
        * 4-En el intent mandar id_medicamento para mostrar su progreso----crear activity para mostrar el proyecto, insertar un cambio de estado solo a una fila
        * 5-Crear alarmmanager para poder ver cada registro
        * */
        String[] valido={"estado"};
        Cursor activo = dbc.query("recetas_catalogo", valido,"id_receta=? and id_usuario=?", new String[]{id_receta, id_usuario}, null, null, null);
        int helper_activo=activo.getCount();
        if(helper_activo!=0){
            for (int z = 1; z <= helper_activo; z++) {
                activo.moveToNext();
            if(activo.getInt(0)==1){
                Toast.makeText(this,"La receta ya esta activa!", Toast.LENGTH_LONG).show();
            }
        else {
                String[] campos = {"id_receta", "id_medicamento"};
                String[] horarios = {"hora", "minuto","id_notificacion"};
                //Intent
                Intent alertIntent = new Intent(this, receiver.class);
                alertIntent.putExtra("id_receta", id_receta);


                //Alarm manager
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                ContentValues intencion = new ContentValues();
                intencion.put("id_receta", Integer.parseInt(id_receta));



                Cursor selectAll = dbc.query("receta_medicamento", campos, "id_receta=?", new String[]{id_receta}, null, null, null);

                int Total = selectAll.getCount();
                if (Total != 0) {
                    for (int i = 1; i <= Total; i++) {
                        selectAll.moveToNext();
                        /////
                        prueba.append("Medicamento: \n");
                        /////
                        int id_medicamento = selectAll.getInt(1);
                        String id_med = String.valueOf(id_medicamento);
                    //Envio otra ves en el intent
                        alertIntent.putExtra("id_medicamento", id_med);

                        Cursor alarmas = dbc.query("notificaciones", horarios, "id_medicamento=?", new String[]{id_med}, null, null, null);
                        int total2 = alarmas.getCount();
                        if (total2 != 0) {
                            for (int j = 1; j <= total2; j++) {
                                alarmas.moveToNext();
                                calendar = Calendar.getInstance();

                                calendar.set(Calendar.HOUR_OF_DAY, alarmas.getInt(0)); // For 1 PM or 2 PM
                                calendar.set(Calendar.MINUTE, alarmas.getInt(1));
                                //Envio otra vez del id_notificacion
                                alertIntent.putExtra("id_notificacion", alarmas.getInt(2));

                                //Crear una tabla con id_usuario-id_notificacion aqui hago el insert y nevio al intent el id_usuario para recuperarlo y mostrar la info

                                try{
                                    id_pendiente=dba.insert("intents", null, intencion);
                                }catch (Exception e){Toast.makeText(getApplicationContext(),"Error en Insert " + e,Toast.LENGTH_LONG).show();}
                                //Intent
                                PendingIntent pendiente = PendingIntent.getBroadcast(this, (int) (long)id_pendiente, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmManager.INTERVAL_DAY, pendiente);

                                String p = String.valueOf(alarmas.getInt(0));
                                String p2 = String.valueOf(alarmas.getInt(1));
                                prueba.append("Hora: " + p + " Minuto: " + p2 + "\n");
                            }
                        }
                    }
                }

                ContentValues agregar = new ContentValues();
                agregar.put("id_usuario", id_usuario);
                agregar.put("id_receta", id_receta);
                agregar.put("estado", 1);
                try {
                    dba.update("recetas_catalogo", agregar, "id_receta=? and id_usuario=?", new String[]{id_receta, id_usuario});
                } catch (Exception e) {
                    System.out.print(e.toString());
                    Toast.makeText(this, "recetas_catalogo error1 en el update", Toast.LENGTH_SHORT).show();
                }
                dbc.close();
                dba.close();
                Toast.makeText(this, "Receta Activada, ahora recibirás las notificaciones!", Toast.LENGTH_LONG).show();
                Intent inte = new Intent(this, home.class);
                startActivity(inte);
                finish();
            }
        }
  }else{
             Toast.makeText(this,"recetas_catalogo error2",Toast.LENGTH_SHORT).show();
         }
    }

    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Activación de notificaciones");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
}
