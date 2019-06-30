/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguridad.modelo;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Alejandro
 */
@Named(value = "archivo")
@Dependent

public class Archivo {
    
    private String ruta;
    private String tipo;
    
    
    public Archivo(){
        
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void mostrarRuta(){
        System.out.println("");
    }
    
}
