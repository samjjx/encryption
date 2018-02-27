package com.gzip;

import com.encryption.CGBE;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by samjjx on 2018/2/27.
 */
public class GzipCGBE {
    public static void main(String[] args) throws IOException {
        int ori = 2;
        for(int i =0;i<13;i++) {
            compressByScale(ori*=2);
        }
    }
    public static void compressByScale(int number) throws IOException {
        System.out.println("=========================");
        System.out.println("Total encrypt\t" + number +"\tnumbers by CGBE");
        Random random = new Random();
        CGBE intersection = new CGBE();
        String compression ="";
        for(int i = 0;i<number;i++){
            long rint = random.nextLong();
            BigInteger rbig = new BigInteger(""+rint);
            compression = compression+intersection.encrypt(rbig);
        }
        byte[] ori = compression.getBytes();
        System.out.println("Original size\t"+ori.length+"\tBytes");
        Gzip gzip = new Gzip();
        byte[] compressed = gzip.compress(compression);
        System.out.println("Compress size\t"+compressed.length+"\tBytes");
    }
}
