package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenDocID {
    private String id = "";

    public GenDocID(InputStream filename) throws IOException, NoSuchAlgorithmException{
        System.out.println("Generating Unique Doc ID...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        InputStream inputStream = filename;
        int read;
        byte[] buffer = new byte[8192];

        MessageDigest digest = MessageDigest.getInstance("MD5");
        while ((read = inputStream.read(buffer)) > 0) {
            digest.update(buffer, 0, read);
        }

        BigInteger bigInt = new BigInteger(1, digest.digest());
        id = bigInt.toString(16);

        endTime = System.nanoTime ();
        System.err.println("[Generate Unique Doc ID] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
        System.out.println("id: " + id);
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
