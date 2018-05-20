package com.example.treasurehunter.Model;

import com.example.treasurehunter.Model.Busqueda;

import java.util.ArrayList;


public class Response {

    public ArrayList<Busqueda> busqueda;
    public ArrayList<Busqueda> getDataB() {
        return busqueda;
    }

    public ArrayList<Logro> logro;
    public ArrayList<Logro> getDataL() {
        return logro;
    }

}
