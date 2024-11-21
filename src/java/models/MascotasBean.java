/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author CruzF
 */
public class MascotasBean {
    private int id;
    private String nombre;
    private String especie;
    private double peso;
    private Date f_nac;  
    private Sexo sexo;   

    public enum Sexo {
        MACHO, HEMBRA
    }

    // Constructor vacío
    public MascotasBean() {
    }

    // Constructor con parámetros
    public MascotasBean(int id, String nombre, String especie, double peso, Date f_nac, Sexo sexo) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.peso = peso;
        this.f_nac = f_nac;
        this.sexo = sexo;
    }

    // Getters y Setters

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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getF_nac() {
        return f_nac;
    }

    public void setF_nac(Date f_nac) {
        this.f_nac = f_nac;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
