/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguridad.controlador;

import com.seguridad.modelo.EncriptDecriptFiles;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Alejandro
 */
@Named(value = "controlDeEncriptacion")
@ViewScoped
public class controlDeEncriptacion implements Serializable{

    /**
     * Creates a new instance of controlDeEncriptacion
     */
    
    private EncriptDecriptFiles encriptDecriptObject;
    private String rutaArchivoDestino ="C:\\Users\\Alejandro\\Documents\\NetBeansProjects\\PruebaWeb5\\web\\resources\\ArchivosEncriptados\\";
    /**
     *
     */
    public controlDeEncriptacion(){
        
       encriptDecriptObject = new EncriptDecriptFiles();
        
    }

    public EncriptDecriptFiles getEncriptDecriptObject() {
        return encriptDecriptObject;
    }

    public void setEncriptDecriptObject(EncriptDecriptFiles encriptDecriptObject) {
        this.encriptDecriptObject = encriptDecriptObject;
    }
        
    public void encriptar() throws Exception{
        encriptDecriptObject.encriptarArchivo(this.encriptDecriptObject.getRutaOrigen(), this.encriptDecriptObject.getPassword(), rutaArchivoDestino+this.encriptDecriptObject.getNombreArchivoDestino());
    }
    
    public void desencriptar() throws Exception{
        encriptDecriptObject.desencriptarArchivo(this.encriptDecriptObject.getRutaOrigen(), this.encriptDecriptObject.getPassword(), rutaArchivoDestino+this.encriptDecriptObject.getNombreArchivoDestino());

    }
     
    
        
    }
    

