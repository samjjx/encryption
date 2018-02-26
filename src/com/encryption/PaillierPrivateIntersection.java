package com.encryption;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;



/**
 * Created by Peipei YI on 5/14/2014.
 */
public class PaillierPrivateIntersection {
    public static final int bitLength = 1024;   //  tuning
    public static final int certainty = 64;     //  tuning
    public static final Paillier paillier = new Paillier(bitLength, certainty);

    public static final int validLength = bitLength - 2;  // highest 2 bits left blank, caused by mod
    public static final int cStep = (int) (validLength / 2.0);
    public static final BitSet bs = new BitSet(validLength);

    public static final int signum = 1; // 1 for positive


    public static final BigInteger TRUE;

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitLength; i++) {
            if (i % 2 == 0) {
                sb.append(1);   // first bit
            } else {
                sb.append(0);
            }
        }

        TRUE = new BigInteger(sb.toString(), 2);
    }

    //    public static final int magLength = (int) Math.ceil(bitLength/8);
//    public static final byte[] mag = new byte[magLength];     // magnitude in big-endian
    public static final byte[] shift = {0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, (byte) 0x80};

//    public static final byte[] shift = new byte[8];

    //    static {
//        for(int i = 0; i < 8; i++){
//            shift[i] = (byte) (1<<i);
//        }
//    }


    /**
     * This encrypt function takes one label (aka. int[]) a time,
     * and return cipher BigInteger array.
     *
     * @param label a lin or lout label
     * @param C     number of distinguished centers
     * @return
     */
    public BigInteger[] encrypt(final int[] label, final int C) {

        Arrays.sort(label);
        int N = (int) Math.ceil(C * 2.0 / validLength);
        BigInteger[] cipher = new BigInteger[N];

        int p = 0;
        int count = 0;
        while (count < N) {
            bs.clear();
            while (p < label.length && label[p] < (count + 1) * cStep) {
                bs.set(label[p++] % cStep * 2);
            }
            cipher[count] = paillier.Encryption(new BigInteger(signum, bs.toByteArray()));
            count++;
        }

        return cipher;
    }

    public static BigInteger[] intersection(final BigInteger[] lin, final BigInteger[] lout) {
        BigInteger[] ret = new BigInteger[lin.length];
        for (int i = 0; i < lin.length; i++) {
            ret[i] = lin[i].multiply(lout[i]).mod(paillier.nsquare);
        }
        return ret;
    }
    public static BigInteger linkAllBits(BigInteger[] result)
    {
    	BigInteger linkResult=BigInteger.ONE;
    	for(int i=0;i<result.length;i++)
    		linkResult.multiply(result[i]).mod(paillier.nsquare);
    	return linkResult;
    }
    public static boolean decrypt(final BigInteger[] ret) {
    	boolean flag=false;
        for (BigInteger b : ret) {
            if (!TRUE.and(paillier.Decryption(b)).equals(BigInteger.ZERO)) {
                flag= true; // short circuit
            }
        }
        return flag;
    }
    
   
	/**
	 * Transform a ArrayList to be an array
	 * @param tempArrayList : the ArrayList
	 * @return : the array
	 */
	public int[] toarray(ArrayList<Integer> tempArrayList)
	{
		int[] result=new int[tempArrayList.size()];
		for(int i=0;i<tempArrayList.size();i++)
			result[i]=tempArrayList.get(i);
		return result;
	}
    public static void main(String[] args) throws IOException {
    	int[] test={1,2,3,4,5,6,7,8,9,10};
       
        String[] files=new String[2];
        files[0]="K:/python/peppy-master/_exp/index/newindex/email/email_tlstart";
		files[1]="K:/python/peppy-master/_exp/index/newindex/email/email_TL";
		PaillierPrivateIntersection intersection=new PaillierPrivateIntersection();
		int times=10;
			

    }
}
