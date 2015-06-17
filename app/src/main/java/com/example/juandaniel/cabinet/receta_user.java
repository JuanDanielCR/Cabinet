package com.example.juandaniel.cabinet;

/**
 * Created by Juan Daniel on 14/06/2015.
 */
public class receta_user {
    int id_receta;
    int id_usuario;
    int estado;
    public receta_user(int receta, int user, int estado){
        this.id_receta=receta;
        this.id_usuario=user;
        this.estado=estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }
}
