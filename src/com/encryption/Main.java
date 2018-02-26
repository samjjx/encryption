package com.encryption;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/** 
 * @author 蒋家鑫  E-mail: samjjx@hotmail.com 
 * @version 创建时间：2015-8-27 下午6:16:33 
 * 类说明 
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void testPP() throws IOException
	{
		String[] files=new String[2];
		files[0]="labellevel/p2p-Gnutella04(in).txt";
		files[1]="labellevel/p2p-Gnutella04(out).txt";
		
		PPTopoLabel pp=new PPTopoLabel(files);
		pp.transLabel();
		System.out.println(pp.center);
		long t0=System.currentTimeMillis();
		pp.CgbeEncrypt(1000);
		System.out.println(System.currentTimeMillis()-t0);
	}
	public static void testTF(HashMap<Integer, ArrayList<Integer>> lin,HashMap<Integer, ArrayList<Integer>> lout) throws IOException
	{
		String[] files=new String[2];
		files[0]="K:/python/peppy-master/_exp/index/newindex/G05/G05_tlstart";
		files[1]="K:/python/peppy-master/_exp/index/newindex/G05/G05_TL";
		
		TFReader tfReader=new TFReader();
		tfReader.iniRead(files);
//		tfReader.showLabel();
		
		System.out.println(tfReader.tfLabel.size());
//		tfReader.readLabelSize("K:/python/peppy-master/_exp/index/newindex/G09/G09_tlstart");
//		tfReader.readLabel("K:/python/peppy-master/_exp/index/newindex/G09/G09_TL");
		tfReader.testDis();
		//tfReader.check(tfReader);
		tfReader.tfr2PP(lin, lout);
		PPTopoLabel pp=new PPTopoLabel(lin,lout);
		long t0=System.currentTimeMillis();
		pp.CgbeEncrypt(100);
		System.out.println(System.currentTimeMillis()-t0);
	}
	
	public static void main(String[] args) throws IOException {
		HashMap<Integer, ArrayList<Integer>> lin=new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> lout=new HashMap<Integer, ArrayList<Integer>>();
		//testPP();
		testPP();
		
	}

}
