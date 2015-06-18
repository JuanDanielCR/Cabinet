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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class contactoPerfil extends ActionBarActivity {

    String name;
    String paterno;
    String materno;
    long telefono;
    int estado=1;
    String correo;
    int prioridad;
    EditText nombre,papa,mama,phone,mail;
    TextView contacto;
    RatingBar priority;

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_perfil);
        setToolbar();
        Bundle recibo;
        recibo=this.getIntent().getExtras();
        id=recibo.getString("name");

        contacto=(TextView) findViewById(R.id.recibo);
        nombre= (EditText) findViewById(R.id.nombrerecibo);
        papa= (EditText) findViewById(R.id.recibopaterno);
        papa.setText(id);
        mama= (EditText) findViewById(R.id.recibomaterno);
        mail= (EditText) findViewById(R.id.correorecibo);
        phone= (EditText) findViewById(R.id.numerorecibo);
        priority=(RatingBar) findViewById(R.id.priority);
        MandarDatos();
    }

    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Mis Contactos");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
    public void MandarDatos(){
        SQL sql = new SQL(this,"CabinetDB", null, 8);
        final SQLiteDatabase db = sql.getReadableDatabase();
        String[] campos = {"id_contacto", "nombre", "paterno","materno","numero","correo","prioridad","estado"};

        Cursor selectAll = db.query("contactos", campos,"id_contacto=?", new String[]{ id }, null, null, null);

        int Total = selectAll.getCount();
        if(Total==0){
            nombre.setText("El usuario ya no existe");
        }else {
            while(selectAll.moveToNext()){
                contacto.setText(selectAll.getString(1)+" "+selectAll.getString(2)+" "+selectAll.getString(3));
                nombre.setText(selectAll.getString(1));
                papa.setText(selectAll.getString(2));
                mama.setText(selectAll.getString(3));
                phone.setText(String.valueOf(selectAll.getInt(4)));
                mail.setText(selectAll.getString(5));
            }
        }
        db.close();
    }

    public void BorrarContacto(View view){
        SQL SQlite= new SQL(this,"CabinetDB",null,8);
        final SQLiteDatabase db= SQlite.getWritableDatabase();

        if(db != null) {
            try {
                db.delete("contactos", "id_contacto=?", new String[]{id});
                Toast.makeText(this, "El contacto se ha borrado con éxito!", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(this, "Cabinet, se ha cometido un error: "+e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        Intent inte= new Intent(this,CabientProfile.class);
        startActivity(inte);
        finish();
    }

    public void ModificarContacto(View view){
        SQL auxiliar= new SQL(this,"CabinetDB",null,8);
        final SQLiteDatabase db=auxiliar.getWritableDatabase();
        name=nombre.getText().toString();
        paterno=papa.getText().toString();
        materno=mama.getText().toString();
        telefono=Long.parseLong(phone.getText().toString());
        correo=mail.getText().toString();
        prioridad= (int) priority.getRating();

        ContentValues datos = new ContentValues();
        datos.put("nombre", name);
        datos.put("paterno", paterno);
        datos.put("materno", materno);
        datos.put("numero", telefono);
        datos.put("correo", correo);
        datos.put("prioridad", prioridad);
        datos.put("estado", estado);

        if(db != null){
            try {
                db.update("contactos", datos, "id_contacto=?", new String[]{id});
                Toast.makeText(this, "Los datos del contacto han sido actualizados con éxito!", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Cabinet: 'No se pudieron actualizar los datos ' " + e,Toast.LENGTH_LONG).show();
            }
        }
        Intent inte= new Intent(this,CabientProfile.class);
        startActivity(inte);
        finish();
    }

    public void AgregarContactoVolver(View v){
        Intent inicio= new Intent(this ,CabinetAltaContacto.class);
        startActivity(inicio);
        finish();
    }
}
