package com.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.BitSet;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



/** 
 * @author 蒋家鑫  E-mail: samjjx@hotmail.com 
 * @version 创建时间：2015-8-26 上午10:46:24 
 * 类说明 
 */
public class AES {
	public String password = "12543243242380";
	public byte[][] cipher;
	public final int speed = 134217728;

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public byte[] encrypt(byte[] content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");  
		    secureRandom.setSeed(password.getBytes()); 
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public byte[] decrypt(byte[] content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");  
		    secureRandom.setSeed(password.getBytes()); 
			kgen.init(128, secureRandom);
			//kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Turn the label(ArrayList) to a byte[]
	 * 
	 * @param tempArrayList
	 *            : The original label
	 * @param total
	 *            : The size of the label
	 * @return
	 */
	public byte[] toarray(ArrayList<Integer> tempArrayList, int total) {
		BitSet bsBitSet = new BitSet(total + 2);
		bsBitSet.set(total + 1);
		for (int i = 0; i < tempArrayList.size(); i++)
			bsBitSet.set(tempArrayList.get(i));
		return bsBitSet.toByteArray();
	}

	
	/**
	 * Test the intersection result
	 * 
	 * @param lin
	 *            : The set of labelin of v
	 * @param lout
	 *            : The set of labelout of u
	 * @param mark
	 *            : The mark bit
	 * @return : True if u can reach v. False if u cannot reach v
	 */
	public boolean intersection(byte[] lin, byte[] lout, int mark) {
		byte[] lin_decryption = decrypt(lin);
		byte[] lout_decryption = decrypt(lout);
		BitSet linBitSet = BitSet.valueOf(lin_decryption);
		BitSet loutBitSet = BitSet.valueOf(lout_decryption);
		linBitSet.clear(mark + 1);
		loutBitSet.clear(mark + 1);
		linBitSet.and(loutBitSet);
		if (linBitSet.isEmpty())
			return false;
		return true;
	}

}
