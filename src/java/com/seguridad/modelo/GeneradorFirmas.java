package com.seguridad.modelo;

import java.io.*;
import java.security.*;
import javax.enterprise.context.Dependent;
import javax.inject.Named;


@Named(value = "GeneradorFirmas")
@Dependent
public class GeneradorFirmas {
    
    private Signature firma;
    
     /* Create a Signature object and initialize it with the private key */
          
    public GeneradorFirmas(PrivateKey privateKey) throws Exception {
       Signature rsa_firma = Signature.getInstance("SHA256withRSA"); 
       rsa_firma.initSign(privateKey);
       this.firma = rsa_firma;
    }
    
    public byte[] firmaDoc (String nombreDoc) throws Exception {
            
        FileInputStream fis = new FileInputStream(nombreDoc);
        BufferedInputStream bufin = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        while (bufin.available() != 0) {
           len = bufin.read(buffer);
           this.firma.update(buffer, 0, len);
         };
        bufin.close();
        byte[] docFirmado = this.firma.sign();
     
        return docFirmado;     
    }
    
    public void copiaFirmaArchivo(byte[] firma, String ruta) throws Exception {
       
        FileOutputStream firmafos = new FileOutputStream(ruta);
        firmafos.write(firma);
        firmafos.close();
        
    }
    
}
   
                    

