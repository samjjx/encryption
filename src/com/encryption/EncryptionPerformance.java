package com.encryption;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * @author ������  E-mail: samjjx@hotmail.com 
 * @version ����ʱ�䣺2015-9-16 ����1:00:10 
 * ��˵�� 
 */
public class EncryptionPerformance {

	/**
	 * @param args
	 */
	int disCenters;
	int[] testLabel;
	ArrayList<Integer> testArrayList;
	public EncryptionPerformance(int disCenters)
	{
		testLabel=new int[1];
		testLabel[0]=1;
		testArrayList=new ArrayList<Integer>();
		testArrayList.add(1);
		this.disCenters=disCenters;
	}
	public void cgbeEncryption(int numberLabels)
	{
		CGBEPrivateIntersection cgbe=new CGBEPrivateIntersection();
		long t0=System.currentTimeMillis();
		for(int i=0;i<numberLabels;i++)
			cgbe.encrypt(testLabel, disCenters);
		t0=System.currentTimeMillis()-t0;
		printTime(t0,numberLabels);
	}
	public void AspeEncryption(int numberLabels,int d)
	{
		ASPE aspe=new ASPE(d,disCenters);
		long t0=System.currentTimeMillis();
		for(int i=0;i<numberLabels;i++)
			aspe.encryptOne(testArrayList);
		t0=System.currentTimeMillis()-t0;
		printTime(t0,numberLabels);
	}
	public void AesEncryption(int numberLabels)
	{
		AES aes=new AES();
		long t0=System.currentTimeMillis();
		for(int i=0;i<numberLabels;i++)
			aes.encrypt(aes.toarray(testArrayList,disCenters));
		t0=System.currentTimeMillis()-t0;
		printTime(t0,numberLabels);
	}
	public void PaillierEncryption(int numberLabels)
	{
		PaillierPrivateIntersection paillier=new PaillierPrivateIntersection();
		long t0=System.currentTimeMillis();
		for(int i=0;i<numberLabels;i++)
			paillier.encrypt(testLabel, disCenters);
		t0=System.currentTimeMillis()-t0;
		printTime(t0,numberLabels);
//		System.out.println(System.currentTimeMillis()-t0);
	}
//	public void printTime(long t0)
//	{
//		t0/=1000;
//		if(t0<60)
//		{
//			System.out.println("The time:"+t0+"s");
//			return ;
//		}
//		long s=t0%60;
//		t0/=60;
//		if(t0<60)
//		{
//			System.out.println("The time:"+t0+"min"+s+"s");
//			return ;
//		}
//		long min=t0%60;
//		System.out.println("The time:"+ t0+"hours"+min+"min"+s+"s");
//	}
	public void printTime(long t0,int numberLabels)
	{
		System.out.println((double)t0/numberLabels);
	}
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		int numberLabels=1000;
		while(scan.hasNext())
		{
			int distinct=scan.nextInt();
			EncryptionPerformance ep=new EncryptionPerformance(distinct);
			ep.PaillierEncryption(numberLabels);
		}
		
	}
}
