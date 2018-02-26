package com.encryption;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Jama.Matrix;

/** 
 * @author 蒋家鑫  E-mail: samjjx@hotmail.com 
 * @version 创建时间：2015-8-26 上午11:30:55 
 * 类说明 
 */
public class PPTopoLabel {

	/**
	 * @param args
	 */
	HashMap<Integer, ArrayList<Integer>> lin=new HashMap<Integer, ArrayList<Integer>>();
	HashMap<Integer, ArrayList<Integer>> lout=new HashMap<Integer, ArrayList<Integer>>();
	int center=0;
	HashMap<Integer, Integer> reflex=new HashMap<Integer, Integer>();
	public PPTopoLabel(String[] files) throws IOException
	{
		lin=getLabel(files[0]);
		lout=getLabel(files[1]);
		center=disCenterTotal(lin, lout);
		System.out.println(center);
	}
	public PPTopoLabel(HashMap<Integer, ArrayList<Integer>> lin, HashMap<Integer, ArrayList<Integer>> lout)
	{
		this.lin=lin;
		this.lout=lout;
		center=disCenterTotal(lin, lout);
		System.out.println(center);
	}
	public HashMap<Integer, ArrayList<Integer>> getLabel(String dataset) throws IOException
	{
		HashMap<Integer, ArrayList<Integer>> Label=new HashMap<Integer, ArrayList<Integer>>();
		FileInputStream in=new FileInputStream(dataset);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String tempLine;
		while((tempLine=br.readLine())!=null)
		{
			String[] array=tempLine.split(" ");
			Label.put(Integer.parseInt(array[0]), new ArrayList<Integer>());
			for(int i=1;i<array.length;i++)
				Label.get(Integer.parseInt(array[0])).add(Integer.parseInt(array[i]));
		}
		return Label;
	}
	public int disCenterTotal(HashMap<Integer, ArrayList<Integer>> labelIn,HashMap<Integer, ArrayList<Integer>> labelOut)
	{
		ArrayList<Integer> center=new ArrayList<Integer>();
		int count=0;
		Set<Integer> keySet=labelIn.keySet();
		for(int key:keySet)
		{
			ArrayList<Integer> temp=labelIn.get(key);
			for(int vertex:temp)
				if(!center.contains(vertex))
				{
					center.add(vertex);
					reflex.put(vertex, count);
					count++;
				}
		}
		keySet=labelOut.keySet();
		for(int key:keySet)
		{
			ArrayList<Integer> temp=labelOut.get(key);
			for(int vertex:temp)
				if(!center.contains(vertex))
				{
					center.add(vertex);
					reflex.put(vertex, count);
					count++;
				}
		}
		return center.size();
	}
	public void transLabel()
	{
		Set<Integer> keyset=lin.keySet();
		for(int key:keyset)
		{
			ArrayList<Integer> temp=lin.get(key);
			ArrayList<Integer> tranArrayList=new ArrayList<Integer>();
			for(int aInt:temp)
				tranArrayList.add(reflex.get(aInt));
			lin.put(key, tranArrayList);
		}
		keyset=lout.keySet();
		for(int key:keyset)
		{
			ArrayList<Integer> temp=lout.get(key);
			ArrayList<Integer> tranArrayList=new ArrayList<Integer>();
			for(int aInt:temp)
				tranArrayList.add(reflex.get(aInt));
			lout.put(key, tranArrayList);
		}
	}
	public void aesEncrypt(int times)
	{
		AES aes=new AES();
		int count=0;
		Set<Integer> keyset=lin.keySet();
		for(int key:keyset)
		{
			aes.encrypt(aes.toarray(lin.get(key),center));
			count++;
			if(count>=times)
				return;
		}
		keyset=lout.keySet();
		for(int key:keyset)
		{
			aes.encrypt(aes.toarray(lout.get(key),center));
			count++;
			if(count>=times)
				return;
		}
	}
	public void aspeEncrypt(int times,int d) 
	{
		ASPE aspe=new ASPE(d,center);
		int count=0;
		Set<Integer> keyset=lin.keySet();
		for(int key:keyset)
		{
			aspe.encryptOne(lin.get(key));
			count++;
			if(count>=times)
				return;
		}
		keyset=lout.keySet();
		for(int key:keyset)
		{
			aspe.encryptOne(lout.get(key));
			count++;
			if(count>=times)
				return;
		}
	}
	public void paillierEncrypt(int times)
	{
		PaillierPrivateIntersection paillier=new PaillierPrivateIntersection();
		int count=0;
		Set<Integer> keyset=lin.keySet();
		for(int key:keyset)
		{
			paillier.encrypt(paillier.toarray(lin.get(key)), center);
			count++;
			if(count>=times)
				return;
		}
		keyset=lout.keySet();
		for(int key:keyset)
		{
			paillier.encrypt(paillier.toarray(lout.get(key)), center);
			count++;
			if(count>=times)
				return;
		}
	}
	public void CgbeEncrypt(int times)
	{
		CGBEPrivateIntersection cgbe=new CGBEPrivateIntersection();
		int count=0;
		Set<Integer> keyset=lin.keySet();
		for(int key:keyset)
		{
			cgbe.encrypt(cgbe.toarray(lin.get(key)), center);
			count++;
			if(count>=times)
				return;
		}
		keyset=lout.keySet();
		for(int key:keyset)
		{
			cgbe.encrypt(cgbe.toarray(lout.get(key)), center);
			count++;
			if(count>=times)
				return;
		}
	}
}
