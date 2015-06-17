package com.example.juandaniel.cabinet;

/**
 * Created by Juan Daniel on 16/06/2015.
 */
public class medicamento {
    int id_medicamento;
    String medicamento, via, dosis;

    public medicamento(int id, String medica, String via, String dosis){
        this.id_medicamento=id;
        this.medicamento=medica;
        this.via=via;
        this.dosis=dosis;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
}
