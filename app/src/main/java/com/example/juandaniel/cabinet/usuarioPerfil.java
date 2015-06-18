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
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class usuarioPerfil extends ActionBarActivity {

    String id;
    EditText nombre;
    EditText paterno;
    EditText materno;
    EditText edad;
    int estado=1;
    int edads;
    Switch sex;
    TextView contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_perfil);
        setToolbar();

        Bundle recibe;
        recibe=this.getIntent().getExtras();
        id=recibe.getString("name");
        nombre= (EditText) findViewById(R.id.nombrerecibo);
        paterno= (EditText) findViewById(R.id.recibopaterno);
        materno= (EditText) findViewById(R.id.recibomaterno);
        contacto=(TextView) findViewById(R.id.recibo);
        edad= (EditText) findViewById(R.id.edadrecibo);
        sex=(Switch)findViewById(R.id.sexorecibo);
        sex.setTextOff("Masculino");
        sex.setTextOn("Femenino");
        DatosUsuario();
    }

    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Mis Usuarios");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }

    public void DatosUsuario(){
        SQL sql = new SQL(this,"CabinetDB", null, 8);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_usuario","nombre", "paterno","materno","edad","sexo","estado"};

        Cursor selectAll = db.query("usuarios", campos,"id_usuario=?", new String[] { id }, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
           nombre.setText("El usuario ya no existe");
        }else {
            while(selectAll.moveToNext()){
                contacto.setText(selectAll.getString(1)+" "+selectAll.getString(2)+" "+selectAll.getString(3));
                    nombre.setText(selectAll.getString(1));
                    paterno.setText(selectAll.getString(2));
                    materno.setText(selectAll.getString(3));
                edads=selectAll.getInt(4);
                String edades=String.valueOf(edads);
                    edad.setText(edades);
                }
            }
            db.close();
        }

        public void BorrarUsuario(View view){
            SQL SQlite= new SQL(this,"CabinetDB",null,8);
            final SQLiteDatabase db= SQlite.getWritableDatabase();

            if(db != null) {
                try {
                    db.delete("usuarios", "id_usuario=?", new String[]{id});
                    Toast.makeText(this, "El familiar se ha borrado con éxito!", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(this, "Cabinet, se ha cometido un erorr: "+e.toString(), Toast.LENGTH_LONG).show();
                }
            }
            Intent inte= new Intent(this,CabientProfile.class);
            startActivity(inte);
            finish();
        }

        public void AgregarUsuarioVolver(View v){
            Intent inicio= new Intent(this ,CabinetAlta.class);
            startActivity(inicio);
            finish();
        }

        public void ModificarUsuario(View view){
        SQL auxiliar= new SQL(this,"CabinetDB",null,8);
        final SQLiteDatabase db=auxiliar.getWritableDatabase();

            String name=nombre.getText().toString();
            String papa=paterno.getText().toString();
            String mama=materno.getText().toString();
            int años=Integer.parseInt(edad.getText().toString());
            String sexo="Masculino";

            ContentValues datos = new ContentValues();
            datos.put("nombre", name);
            datos.put("paterno", papa);
            datos.put("materno", mama);
            datos.put("edad", años);
            datos.put("sexo", sexo);
            datos.put("estado", estado);
            if(db != null){
                try {
                    db.update("usuarios", datos, "id_usuario=?", new String[]{id});
                    Toast.makeText(this, "Los datos del usuario han sido actualizados con éxito!", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Cabinet: 'No se pudieron actualizar los datos ' " + e,Toast.LENGTH_LONG).show();
                }
            }
            Intent inte= new Intent(this,CabientProfile.class);
            startActivity(inte);
            finish();
    }
}
