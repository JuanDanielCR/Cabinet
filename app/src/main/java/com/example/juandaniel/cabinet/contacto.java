package com.example.juandaniel.cabinet;

/**
 * Created by Juan Daniel on 12/06/2015.
 */
//Clase contacto
public class contacto {
    String nombre,paterno,materno,correo;
    int estado,numero,prioridad;
    int id;

    public contacto(String name,String father,String mother,String mail, int number, int priority, int id){
        this.nombre=name;
        this.paterno=father;
        this.materno=mother;
        this.correo=mail;
        this.numero=number;
        this.prioridad=priority;
        this.id=id;
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

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }
}
