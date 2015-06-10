package com.example.juandaniel.cabinet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan Daniel on 07/06/2015.
 */
public class SQL extends SQLiteOpenHelper {

    String tableRecetas="CREATE TABLE recetas(id_receta INTEGER PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER NOT NULL, duracion INTEGER NOT NULL, estado INTEGER NOT NULL);";
    String tableRecetaInfo="CREATE TABLE recetas_info(id_receta INTEGER NOT NULL, horas INTEGER NOT NULL, medicamento TEXT NOT NULL, dosis TEXT NOT NULL, via TEXT NOT NULL);";
    String tableAlerta="CREATE TABLE alertas(id_alerta INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT NOT NULL, longitud TEXT NOT NULL, latitud TEXT NOT NULL);";
    String tableUsuario="CREATE TABLE usuarios(id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, paterno TEXT NOT NULL, materno TEXT NOT NULL, edad INTEGER NOT NULL, sexo TEXT NOT NULL, estado INTEGER NOT NULL);";
    String tableContacto="CREATE TABLE contactos(id_contacto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, paterno TEXT NOT NULL, materno TEXT NOT NULL, numero INTEGER NOT NULL, correo TEXT NOT NULL, prioridad INTEGER NOT NULL, estado INTEGER NOT NULL);";

    public SQL(Context context, String DBname,
                       SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, DBname, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableRecetas);
        db.execSQL(tableRecetaInfo);
        db.execSQL(tableAlerta);
        db.execSQL(tableUsuario);
        db.execSQL(tableContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
