package com.encryption;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * @author ������  E-mail: samjjx@hotmail.com 
 * @version ����ʱ�䣺2015-9-18 ����4:59:03 
 * ��˵�� 
 */
public class QueryPerformance {

	/**
	 * @param args
	 */
	int disCenters;
	int[] testLabel;
	ArrayList<Integer> testArrayList;
	BigInteger[] aCipher;
	BigInteger[] bCipher;
	double[] aspeACipher;
	double[] aspeBCipher;
	byte[] aesACihper;
	byte[] aesBCipher;
	public QueryPerformance(int disCenters)
	{
		testLabel=new int[1];
		testLabel[0]=1;
		testArrayList=new ArrayList<Integer>();
		testArrayList.add(1);
		this.disCenters=disCenters;
	}
//	public void printTime(long t0)
//	{
//		t0/=1000;
//		if(t0<60)
//		{
//			System.out.println("The time:"+t0+"ms");
//			return ;
//		}
//		long ms=t0%60;
//		t0/=60;
//		if(t0<60)
//		{
//			System.out.println("The time:"+t0+"min"+ms+"ms");
//			return ;
//		}
//		long min=t0%60;
//		System.out.println("The time:"+ t0+"hours"+min+"min"+ms+"ms");
//	}
	public void cgbeEncryption(int queryTimes)
	{
//		System.out.println("=============Begin===============");
		CGBEPrivateIntersection cgbeIntersection=new CGBEPrivateIntersection();
		aCipher=cgbeIntersection.encrypt(testLabel, disCenters);
		bCipher=cgbeIntersection.encrypt(testLabel, disCenters);
		long t0=System.currentTimeMillis();
		for(int i=0;i<queryTimes;i++)
			cgbeIntersection.intersection(aCipher, bCipher);
		t0=System.currentTimeMillis()-t0;
		System.out.print("\t");
		printTime(t0,queryTimes);
		
		BigInteger cipher=cgbeIntersection.intersection(aCipher, bCipher);
		t0=System.currentTimeMillis();
		for(int i=0;i<queryTimes;i++)
			cgbeIntersection.decrypt(cipher);
		t0=System.currentTimeMillis()-t0;
		System.out.print("\t");
		printTime(t0, queryTimes);
//		System.out.println("==============End================");
	}
	public void aspeEncryption(int queryTimes)
	{
		ASPE aspe=new ASPE(100,disCenters);
		aspeACipher=aspe.encryptOne(testArrayList);
		aspeBCipher=aspe.encryptOne(testArrayList);
		long t0=System.currentTimeMillis();
		for(int i=0;i<queryTimes;i++)
			aspe.queryOnce(aspeACipher, aspeBCipher);
		t0=System.currentTimeMillis()-t0;
		printTime(t0,queryTimes);
	}
	public void aesEncryption(int queryTimes)
	{
		AES aes =new AES();
		aesACihper=aes.encrypt(aes.toarray(testArrayList,disCenters));
		aesBCipher=aes.encrypt(aes.toarray(testArrayList,disCenters));
		long t0=System.currentTimeMillis();
		for(int i=0;i<queryTimes;i++)
			aes.intersection(aesACihper, aesBCipher, disCenters);
		t0=System.currentTimeMillis()-t0;
		printTime(t0,queryTimes);
	}
//	public void checkPaillier()
//	{
//		PaillierPrivateIntersection paillier=new PaillierPrivateIntersection();
//		aCipher=paillier.encrypt(testLabel, disCenters);
//		bCipher=paillier.encrypt(testLabel, disCenters);
//		BigInteger[] result=paillier.intersection(aCipher, bCipher);
//		BigInteger allResult=paillier.linkAllBits(result);
//		
//	}
	public void paillierEncryption(int queryTimes)
	{
//		System.out.println("=============Begin===============");
		PaillierPrivateIntersection paillier=new PaillierPrivateIntersection();
		aCipher=paillier.encrypt(testLabel, disCenters);
		bCipher=paillier.encrypt(testLabel, disCenters);
		long t0=System.currentTimeMillis();
		for(int i=0;i<queryTimes;i++)
			paillier.intersection(aCipher, bCipher);
		t0=System.currentTimeMillis()-t0;
		
		printTime(t0,queryTimes);
		System.out.print("\t");
		
		BigInteger[] cipherIntersection=paillier.intersection(aCipher, bCipher);
		t0=System.currentTimeMillis();
		for(int i=0;i<queryTimes;i++)
			paillier.decrypt(cipherIntersection);
		t0=System.currentTimeMillis()-t0;
		printTime(t0, queryTimes);
		System.out.print("\t");
//		System.out.println("==============End================");
		
	}
	public void printTime(long t0,int queryTimes)
	{
		System.out.print((double)t0/queryTimes);
	}
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("Centers \t IntersectionTime \t DescryptionTime \t");
		ArrayList<Integer> list=new ArrayList<Integer>();
		while(scan.hasNext())
		{
			int temp=scan.nextInt();
			if(temp!=-1)
				list.add(temp);
			else
				break;
		}
//		System.out.println("Paillier");
//		for(int distinct:list)
//		{
//			QueryPerformance qp=new QueryPerformance(distinct);
//			int queryTimes=100;
//			System.out.print(distinct+"\t");
//			qp.paillierEncryption(queryTimes);
//			System.out.println();
//		}
		System.out.println("CGBE");
		for(int distinct:list)
		{
			QueryPerformance qp=new QueryPerformance(distinct);
			int queryTimes=100;
			System.out.print(distinct+"\t");
			qp.cgbeEncryption(queryTimes);
			System.out.println();
		}
		System.out.println("AES");
		for(int distinct:list)
		{
			QueryPerformance qp=new QueryPerformance(distinct);
			int queryTimes=100;
			System.out.print(distinct+"\t");
			qp.aesEncryption(queryTimes);
			System.out.println();
		}
	}
}
