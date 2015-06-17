package com.example.juandaniel.cabinet;

/**
 * Created by Juan Daniel on 13/06/2015.
 */
public class receta {
    int id;
    String nombre;
    int duracion;

    public receta(String nombre,int duracion, int id){
        this.id=id;
        this.nombre=nombre;
        this.duracion=duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
