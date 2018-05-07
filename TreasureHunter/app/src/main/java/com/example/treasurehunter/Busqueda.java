package com.example.treasurehunter;

import java.io.Serializable;

public class Busqueda  implements Serializable {
    private int id;
    private String titulo;
    private int nivel;
    private String descripcion;
    private String contraseña;
    private double longitud;
    private double latitud;
    private String terminada;
    private double puntos[];

    public Busqueda(int id, String titulo, int nivel, String descripcion, String contraseña, double longitud, double latitud, String terminada, double[] puntos) {
        this.id = id;
        this.titulo = titulo;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.contraseña = contraseña;
        this.longitud = longitud;
        this.latitud = latitud;
        this.terminada = terminada;
        this.puntos = puntos;
    }

    public Busqueda() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getTerminada() {
        return terminada;
    }

    public void setTerminada(String terminada) {
        this.terminada = terminada;
    }

    public double[] getPuntos() {
        return puntos;
    }

    public void setPuntos(double[] puntos) {
        this.puntos = puntos;
    }
}
