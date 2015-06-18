package com.example.juandaniel.cabinet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan Daniel on 07/06/2015.
 */
public class SQL extends SQLiteOpenHelper {
    //catalogo usuario-receta
    String tableCatalogo="CREATE TABLE recetas_catalogo(id_receta INTEGER NOT NULL, id_usuario INTEGER NOT NULL, estado INTEGER NOT NULL);";
    //catalogo medicamento-horario
    String tableNotificacion="CREATE TABLE notificaciones(id_notificacion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,id_receta INTEGER NOT NULL, id_medicamento INTEGER NOT NULL, hora INTEGER NOT NULL, minuto INTEGER NOT NULL, tomada INTEGER NOT NULL);";
    //notif_usuario
    String tableNotiUsuario="CREATE TABLE notiusuario(id_notificacion INTEGER NOT NULL, id_usuario INTEGER NOT NULL);";
    //medicamento individual
    String tableMedica_Receta="CREATE TABLE receta_medicamento(id_receta INTEGER NOT NULL, id_medicamento INTEGER NOT NULL);";
    String tableMedicamentos="CREATE TABLE medicamentos(id_medicamento INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, medicamento TEXT NOT NULL, dosis TEXT NOT NULL, via TEXT NOT NULL, numero INTEGER NOT NULL);";
    //Recetas
    String tableRecetas="CREATE TABLE recetas(id_receta INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, duracion INTEGER NOT NULL);";
    //intents creados
    String tableIntents="CREATE TABLE intents(id_intent INTEGER PRIMARY KEY AUTOINCREMENT, id_receta INTEGER NOT NULL);";
    //Admin de usuarios
    String tableUsuario="CREATE TABLE usuarios(id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, paterno TEXT NOT NULL, materno TEXT NOT NULL, edad INTEGER NOT NULL, sexo TEXT NOT NULL, estado INTEGER NOT NULL);";
    String tableContacto="CREATE TABLE contactos(id_contacto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, paterno TEXT NOT NULL, materno TEXT NOT NULL, numero INTEGER NOT NULL, correo TEXT NOT NULL, prioridad INTEGER NOT NULL, estado INTEGER NOT NULL);";

    //Alerta
    String tableAlerta="CREATE TABLE alertas(id_alerta INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT NOT NULL, longitud TEXT NOT NULL, latitud TEXT NOT NULL);";

    public SQL(Context context, String DBname,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBname, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableRecetas);
        db.execSQL(tableMedicamentos);
        db.execSQL(tableAlerta);
        db.execSQL(tableUsuario);
        db.execSQL(tableContacto);
        db.execSQL(tableNotificacion);
        db.execSQL(tableCatalogo);
        db.execSQL(tableMedica_Receta);
        db.execSQL(tableIntents);
        db.execSQL(tableNotiUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS recetas;" );
        db.execSQL("DROP TABLE IF EXISTS recetas_catalogo;" );
        db.execSQL("DROP TABLE IF EXISTS receta_medicamento;" );
        db.execSQL("DROP TABLE IF EXISTS medicamentos;" );
        db.execSQL("DROP TABLE IF EXISTS notificaciones;" );
        db.execSQL("DROP TABLE IF EXISTS intents;" );
        db.execSQL("DROP TABLE IF EXISTS notiusuario;");
        db.execSQL(tableRecetas);
        db.execSQL(tableMedicamentos);
        db.execSQL(tableNotificacion);
        db.execSQL(tableCatalogo);
        db.execSQL(tableMedica_Receta);
        db.execSQL(tableIntents);
        db.execSQL(tableNotiUsuario);
    }
}
