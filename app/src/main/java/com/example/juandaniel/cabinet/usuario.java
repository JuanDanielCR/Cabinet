package com.example.juandaniel.cabinet;

/**
 * Created by Juan Daniel on 10/06/2015.
 */
public class usuario {
    int id;
    String nombre;
    String paterno;
    String materno;
    int edad;
    String sexo;
    public usuario(){
        this.nombre="";
        this.materno="";
        this.paterno="";
        this.edad=0;
        this.sexo="";
    }
    public usuario(String name, String father, String mother, int age, String sex,int id){
        this.nombre=name;
        this.materno=father;
        this.paterno=mother;
        this.edad=age;
        this.sexo=sex;
        this.id=id;
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

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
