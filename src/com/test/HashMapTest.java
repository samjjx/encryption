package com.test;

import java.util.ArrayList;
import java.util.HashMap;

/** 
 * @author ������  E-mail: samjjx@hotmail.com 
 * @version ����ʱ�䣺2015-8-3 ����12:26:50 
 * ��˵�� 
 */
public class HashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
		hm.put(1, 3);
		hm.put(2, 5);
		hm.put(3, 4);
		hm.put(2, 1);
		System.out.println(hm);
		
	}
	public static void test(HashMap<Integer,ArrayList<Integer>> temp)
	{
		temp.remove(1111);
	}

}
