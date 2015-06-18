package com.example.juandaniel.cabinet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Juan Daniel on 17/06/2015.
 */
public class receiver extends BroadcastReceiver {
    String id_receta, id_medica, id_notificacion;
    private NotificationManager nm;
    private static final int ID_NOTIFICACION_CREAR = 1;
    public Notification notificacion;
    public PendingIntent intencionPendiente;


    @Override
    public void onReceive(Context context, Intent intent) {
        id_receta=intent.getStringExtra("id_receta");
        id_medica=intent.getStringExtra("id_medicamento");
        id_notificacion=intent.getStringExtra("id_notificacion");

        Toast.makeText(context, "Es hora del midcamento", Toast.LENGTH_SHORT).show();
        Intent service1 = new Intent(context, notificacionA.class);

        nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificacion = new Notification(
                R.mipmap.logo,
                "Recordatorio Médico",//Texto arriba
                System.currentTimeMillis() );
        //Agregando sonido a la notificacion
        notificacion.defaults|=Notification.DEFAULT_SOUND;
        //Vibracion
        notificacion.defaults|=Notification.DEFAULT_VIBRATE;
        //led
        notificacion.defaults|=Notification.DEFAULT_LIGHTS;
        //cerar
        notificacion.flags= Notification.FLAG_AUTO_CANCEL;

        Intent registrar= new Intent(context,notificacionA.class);
        Bundle envio= new Bundle();
        envio.putString("id_receta",id_receta);
        envio.putString("id_medicamento",id_medica);
        envio.putString("id_notificacion",id_notificacion);
        registrar.putExtras(envio);

        intencionPendiente = PendingIntent.getActivity(context, 0,registrar, 0);
        //Texto abajo
        notificacion.setLatestEventInfo(context, "Recordatorio Cabinet","Tienes un medicamento registrado a esta hora", intencionPendiente);
        nm.notify(ID_NOTIFICACION_CREAR, notificacion);


    }
}
