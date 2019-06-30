package com.seguridad.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 *
 * @author RodrigoCastro
 */
public class Main_Encriptador {

    public static void main(String[] args) throws Exception {
        
        byte[] file_encrypted = null;
        byte[] file_decrypted = null;
        String nombreArchivoPlano = "C:\\tmp\\licencia.txt";
        String key = "abcdefghijklmop";
        
        EncriptDecriptFiles encriptador = new EncriptDecriptFiles();
        file_encrypted = encriptador.encriptarArchivo(nombreArchivoPlano, key, "C:\\tmp\\hashing_encrypt.txt");
        encriptador.desencriptarArchivo("", key, "C:\\tmp\\hashing_decrypt.txt");  
                
    }
    
}


