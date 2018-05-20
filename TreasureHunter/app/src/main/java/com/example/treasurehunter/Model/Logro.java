package com.example.treasurehunter.Model;

public class Logro {
     private String titulo;
     private int monedas;
     private String descripcion;
     private int item;

    public Logro(String titulo, int monedas, String descripcion, int item) {
        this.titulo = titulo;
        this.monedas = monedas;
        this.descripcion = descripcion;
        this.item = item;
    }

    public Logro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
