package com.example.juandaniel.cabinet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


public class Emergencia extends ActionBarActivity {

    private NotificationManager nm;
    private static final int ID_NOTIFICACION_CREAR = 1;
    private LocationManager lm;
    public Notification notificacion;
    public PendingIntent intencionPendiente;

    Double latitud=0.0;
    Double longitud=0.0;
    Double altitud=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);
        setToolbar();
    }

    public void setToolbar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Emergencia");

        setSupportActionBar(toolbar);
    }
    //Metodo para servicio con geolocalizacion
    public void IniciarEmergencia(View view){

        nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        lm=(LocationManager)  getSystemService(LOCATION_SERVICE);

        notificacion = new Notification(
                R.mipmap.logo,
                "Alerta Cabinet",//Texto arriba
                System.currentTimeMillis() );
        //Agregando sonido a la notificacion
        notificacion.defaults|=Notification.DEFAULT_SOUND;
        //Vibracion
        notificacion.defaults|=Notification.DEFAULT_VIBRATE;
        //led
        notificacion.defaults|=Notification.DEFAULT_LIGHTS;

        intencionPendiente = PendingIntent.getActivity(
                this, 0, new Intent(this, MainActivity.class), 0);
        //Texto abajo
        notificacion.setLatestEventInfo(this, "Cabinet te ayuda",
                "Hemos enviado una señal de ayuda a tus contactos resgitrados en Cabinet", intencionPendiente);

        nm.notify(ID_NOTIFICACION_CREAR, notificacion);
        Criteria criterio = new Criteria();
        criterio.setCostAllowed(false);
        criterio.setAltitudeRequired(false);
        criterio.setAccuracy(Criteria.ACCURACY_FINE);

        Location localizacion = lm.getLastKnownLocation(lm.getBestProvider(criterio, true));
        Double longitude=localizacion.getLongitude();
        Double latitude=localizacion.getLatitude();
        String pos="Latitud: "+longitude.toString()+"\n Longitud: "+latitude.toString();
        Toast.makeText(getApplicationContext(), pos, Toast.LENGTH_SHORT).show();
    }
}
