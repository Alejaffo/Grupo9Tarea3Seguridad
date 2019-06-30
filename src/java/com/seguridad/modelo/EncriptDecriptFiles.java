package com.seguridad.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.FileInputStream;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

        

/**
 *
 * @author RodrigoCastro
 */

@Named(value = "EncriptDecriptFiles")
@Dependent
public class EncriptDecriptFiles {
    
    
    private String rutaOrigen;
    private String password;
    private String nombreArchivoDestino;

    public String getNombreArchivoDestino() {
        return nombreArchivoDestino;
    }

    public void setNombreArchivoDestino(String nombreArchivoDestino) {
        this.nombreArchivoDestino = nombreArchivoDestino;
    }

    public String getRutaOrigen() {
        return rutaOrigen;
    }

    public void setRutaOrigen(String rutaOrigen) {
        this.rutaOrigen = rutaOrigen;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public byte[] encriptarArchivo (String nombreArchivo, String key, String ruta) throws Exception{

         FileInputStream fileInputStream = null;
         byte[] encrypted = null;
         File file = new File(nombreArchivo);
         byte[] archivo = new byte[(int) file.length()];

         //read file into bytes[]
         fileInputStream = new FileInputStream(file);
         fileInputStream.read(archivo);
         encrypted = encrypt(archivo, key);
         Path archivo_enc = Paths.get(ruta);
         Files.write(archivo_enc, encrypted);
         
         return encrypted;
         
     }
    
    
    public void desencriptarArchivo (String rutaEncriptado, String key, String ruta) throws Exception{
       
        FileInputStream fileInputStream = null;
        File file = new File(rutaEncriptado);
        byte[] archivoEnBytes = new byte[(int) file.length()];
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(archivoEnBytes);
        
        
        byte[] decrypted = decrypt(archivoEnBytes,key);
        
        try {
            Path archivo = Paths.get(ruta);
            Files.write(archivo, decrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }  
     
    }
     
public static byte[] encrypt(byte[] plainFile, String key) throws Exception {
       // byte[] clean = plainText.getBytes();

        // Generating IV.
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Hashing key.
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(key.getBytes("UTF-8"));
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(plainFile);

        // Combine IV and encrypted part.
        byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);

        return encryptedIVAndText;
    }


    public static byte[] decrypt(byte[] encryptedIvTextBytes, String key) throws Exception {
        int ivSize = 16;
        int keySize = 16;

        // Extract IV.
        byte[] iv = new byte[ivSize];
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extract encrypted part.
        int encryptedSize = encryptedIvTextBytes.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

        // Hash key.
        byte[] keyBytes = new byte[keySize];
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(key.getBytes());
        System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Decrypt.
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        return decrypted;
    }
    
    private static byte[] hashPassword(String password, byte[] salt) throws Exception
    {
        byte[] hash = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            hash = md.digest(password.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return hash;
    }
    
    
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
