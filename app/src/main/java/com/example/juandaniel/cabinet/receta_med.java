package com.example.juandaniel.cabinet;

/**
 * Created by Juan Daniel on 16/06/2015.
 */
public class receta_med {
    int id_medicamento;
    int id_receta;

    public receta_med(int rec, int med){
        this.id_medicamento=med;
        this.id_receta=rec;
    }

    public int getid_medicamento() {
        return id_medicamento;
    }

    public void setid_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

}
