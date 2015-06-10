package com.example.juandaniel.cabinet;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by Juan Daniel on 07/06/2015.
 */
public class ServicioMedicamentos extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this,"Oncreate del Servicio",Toast.LENGTH_LONG).show();
    }


    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {

        Toast.makeText(this,"Servicio arrancado "+ idArranque,
                Toast.LENGTH_SHORT).show();

        //reproductor.start();
        return START_STICKY;
    }



@Override
    public void onDestroy() {
        super.onDestroy();
    Toast.makeText(getApplicationContext(),"Servicio Detenido",Toast.LENGTH_SHORT).show();
    }


@Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
