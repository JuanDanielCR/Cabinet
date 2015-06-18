package com.example.juandaniel.cabinet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class SacHome extends ActionBarActivity {

    TimePicker hora;
    int horas,minuto;
    Calendar horajava;
    TextView java,android;
    String txthora,txtminuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sac_home);
        hora=(TimePicker) findViewById(R.id.hora);

    }

    public void notificar(View view){

        horas=hora.getCurrentHour();
        minuto=hora.getCurrentMinute();

        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, horas); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, minuto);

        // Define our intention of executing AlertReceiver
        Intent alertIntent = new Intent(this,receiver.class);
        alertIntent.putExtra("envio","id1");
        alertIntent.putExtra("envio2","id2");
        // Allows you to schedule for your application to do something at a later date
        // even if it is in he background or isn't active
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendiente;
        pendiente=PendingIntent.getBroadcast(this, 0, alertIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        // set() schedules an alarm to trigger
        // Trigger for alertIntent to fire in 5 seconds
        // FLAG_UPDATE_CURRENT : Update the Intent if active
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmManager.INTERVAL_DAY, pendiente);


        Toast.makeText(this, "yahala listo", Toast.LENGTH_SHORT).show();
    }
}
