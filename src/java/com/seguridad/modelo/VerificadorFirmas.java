package com.seguridad.modelo;


import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class VerificadorFirmas {

private byte[] doc;
private byte[] firmaOrig;
private byte[] clavePubFirma;

    public VerificadorFirmas(byte[] doc, byte[] firmaOrig, byte[] clavePubFirma) {
        this.doc = doc;
        this.firmaOrig = firmaOrig;
        this.clavePubFirma = clavePubFirma;
    }

  


/**        
public VerificadorFirmas (byte[] documento, byte[] firma, byte[] clavePub) { 
   
   this.doc = documento;    
   this.firmaOrig = firma;
   this.clavePubFirma = clavePub;
}
*/

public boolean verificaFirma () throws Exception {
   
    X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(this.clavePubFirma);

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey clavePublica = keyFactory.generatePublic(pubKeySpec);

    Signature sig = Signature.getInstance("SHA256withRSA"); 
    sig.initVerify(clavePublica);
    sig.update(this.doc);
    return sig.verify(this.firmaOrig);
            
}


}