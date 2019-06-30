/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguridad.controlador;

import com.seguridad.modelo.Archivo;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Alejandro
 */
@Named(value = "controlDeArchivo")
@ViewScoped
public class controlDeArchivo implements Serializable{
    
    private Archivo archivo;

    /**
     * Creates a new instance of controladorArchivo
     */
    
    
    public controlDeArchivo() {
        archivo = new Archivo();
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public void algo(){
        String s =this.archivo.getRuta();
        System.out.println("");
    }

    
}
