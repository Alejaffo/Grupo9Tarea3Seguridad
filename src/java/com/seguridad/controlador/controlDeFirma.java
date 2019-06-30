/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguridad.controlador;

import com.seguridad.modelo.GeneradorFirmas;
import com.seguridad.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Alejandro
 */
@Named(value = "controlDeFirma")
@ViewScoped
public class controlDeFirma implements Serializable{

    
    private GeneradorFirmas genFirmas;
    private String nombreUsuario;
    
    private String rutaArchivoFirmar;
    private String direccionKeyPrivadas = "C:\\Users\\Alejandro\\Documents\\NetBeansProjects\\PruebaWeb5\\web\\resources\\ClavesPrivadas\\";
    private String direccionFirmas = "C:\\Users\\Alejandro\\Documents\\NetBeansProjects\\PruebaWeb5\\web\\resources\\ArchivosFirma\\";
    private String archivoFirma;
    /**
     * Creates a new instance of controlDeFirma
     */
    public controlDeFirma() throws NoSuchAlgorithmException, InvalidKeySpecException, Exception {
        
        
        //byte[] bytePrivada = fileToBytes(direccionKeyPrivadas+nombreUsuario+".prv");
        byte[] bytePrivada = fileToBytes("C:\\Users\\Alejandro\\Documents\\NetBeansProjects\\PruebaWeb5\\web\\resources\\ClavesPrivadas\\joseUsuario.prv");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivada);
        System.out.print("");
        PrivateKey privatekey = kf.generatePrivate(privateKeySpec);
        //PrivateKey privatekey = kf.generatePrivate(new PKCS8EncodedKeySpec(bytePrivada));
        genFirmas = new GeneradorFirmas(privatekey);
        
        
    }

    public String getDireccionFirmas() {
        return direccionFirmas;
    }

    public void setDireccionFirmas(String direccionFirmas) {
        this.direccionFirmas = direccionFirmas;
    }

    public String getArchivoFirma() {
        return archivoFirma;
    }

    public void setArchivoFirma(String archivoFirma) {
        this.archivoFirma = archivoFirma;
    }
    
   
    
    public void firmarArchivo() throws Exception{
        byte[] byteFirma = genFirmas.firmaDoc(this.direccionKeyPrivadas+nombreUsuario+".prv");
        
        genFirmas.copiaFirmaArchivo(byteFirma, this.direccionFirmas+nombreUsuario+".frm");
        
    }
    
    public void verificarArchivo() {
        
    }

    public String getRutaArchivoFirmar() {
        return rutaArchivoFirmar;
    }

    public void setRutaArchivoFirmar(String rutaArchivoFirmar) {
        this.rutaArchivoFirmar = rutaArchivoFirmar;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDireccionKeyPrivadas() {
        return direccionKeyPrivadas;
    }

    public void setDireccionKeyPrivadas(String direccionKeyPrivadas) {
        this.direccionKeyPrivadas = direccionKeyPrivadas;
    }

    
    
    public GeneradorFirmas getGenFirmas() {
        return genFirmas;
    }

    public void setGenFirmas(GeneradorFirmas genFirmas) {
        this.genFirmas = genFirmas;
    }
    
    
    
    public static byte[] fileToBytes (String archivo) {
       try{
           File file = new File(archivo);
           //init array with file length
            byte[] bytesArray = new byte[(int) file.length()]; 

            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
        
            return bytesArray;

            }
            catch(IOException e ){
            
            return null;
            
        }
    }
}
