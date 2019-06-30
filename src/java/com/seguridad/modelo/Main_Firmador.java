package com.seguridad.modelo;

import java.io.*;
import java.security.*;
//import Seguridad.*;
 

public class Main_Firmador {
    
    public static final String MAIN_FOLDER = "C:\\tmp\\";
    public static final int largo_clave = 1024;
    
    public static void main(String[] args) {

       String nombreArchivo = MAIN_FOLDER + "1.png"; 
               
   /* Generate a DSA signature */
    try{

            /* Generate a key pair */
            
            GeneradorClaves keyGenerator = new GeneradorClaves(largo_clave);
            PrivateKey priv = keyGenerator.getPrivateKey();
            PublicKey pub = keyGenerator.getPublicKey();

            GeneradorFirmas firmGenerator = new GeneradorFirmas(priv);
            byte[] realSig = firmGenerator.firmaDoc(nombreArchivo);
        
            /* Save the signature in a file */
           firmGenerator.copiaFirmaArchivo(realSig, MAIN_FOLDER + "firmaultimo"); 
 
           /* Save the public key in a file */
           keyGenerator.copiarPubKArchivo(pub, MAIN_FOLDER + "publicKeyultima");
                    
            VerificadorFirmas verificadorValida = new VerificadorFirmas(fileToBytes(MAIN_FOLDER + "1.png"),fileToBytes(MAIN_FOLDER + "firmaultimo"),fileToBytes(MAIN_FOLDER + "publicKeyultima"));
            if(verificadorValida.verificaFirma()== true){
                System.out.println("Verifica");
            }
            else{
                System.out.println("No verifica");

            }
            
             VerificadorFirmas verificadorNoValida = new VerificadorFirmas(fileToBytes(MAIN_FOLDER + "1.png"),fileToBytes(MAIN_FOLDER + "firma1"),fileToBytes(MAIN_FOLDER + "publicKeyultima"));
            if(verificadorNoValida.verificaFirma()== true){
                System.out.println("Verifica");
            }
            else{
                System.out.println("No verifica");

            }
            

        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
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

    





