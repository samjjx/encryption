package com.test;

import java.util.LinkedList;

/** 
 * @author ������  E-mail: samjjx@hotmail.com 
 * @version ����ʱ�䣺2015-8-25 ����6:27:57 
 * ��˵�� 
 */
public class LinkedListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * @Title: main
		 * @Description: TODO(������һ�仰�����������������)
		 * @param:   
		 * @return:   
		 * @throws
		 */
		LinkedList<Integer> test=new LinkedList<Integer>();
		test.add(3);
		test.add(6);
		test.add(8);
		test.remove(1);
		for(int i=0;i<test.size();i++)
			System.out.println(test.get(i));
	}

}
