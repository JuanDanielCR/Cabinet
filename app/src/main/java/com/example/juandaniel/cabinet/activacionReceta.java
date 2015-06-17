package com.example.juandaniel.cabinet;

import android.content.ContentValues;
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


public class activacionReceta extends ActionBarActivity {
    TextView datos2;
    int activacion;
    String id_receta,id_usuario;
    Button activo;
    private static int id_btn=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activacion_receta);
        datos2=(TextView)findViewById(R.id.activacion);
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
        SQL sql = new SQL(this,"CabinetDB", null, 4);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_receta","id_medicamento"};

        Cursor selectAll = db.query("recetas_medicamentos", campos, "id_receta=?", new String[]{id_receta}, null, null, null);

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
        SQL SQlite= new SQL(this,"CabinetDB",null,4);
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
        SQL auxiliar= new SQL(this,"CabinetDB",null,4);
        final SQLiteDatabase db=auxiliar.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put("estado", 1);
        if(db != null){
            try {
                db.update("recetas_catalogo", datos, "id_usuario=?", new String[]{id_usuario});
                Toast.makeText(this, "La receta ha sido activada, ahora recibirás las notificaciones que creaste!", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Cabinet: 'No se pudieron actualizar los datos ' " + e,Toast.LENGTH_LONG).show();
            }
        }
        Intent inte= new Intent(this,home.class);
        startActivity(inte);
    }

    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Inicio");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
}
