package com.example.solucion.modelo;

import java.io.Serializable;
import java.util.Date;

public class Registro implements Serializable {

private int id;
private String fecha;
private int minutos;
private String descripcion;
private double peso;
private double imc;
private double grasacorporal;


    public Registro(int id, String fecha, String descripcion, int minutos, double peso, double imc, double grasacorporal) {
        this.id = id;
        this.fecha = fecha;
        this.minutos = minutos;
        this.descripcion = descripcion;
        this.peso = peso;
        this.imc = imc;
        this.grasacorporal = grasacorporal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getGrasacorporal() {
        return grasacorporal;
    }

    public void setGrasacorporal(double grasacorporal) {
        this.grasacorporal = grasacorporal;
    }
}
