package com.test;

import java.util.LinkedList;

/** 
 * @author 蒋家鑫  E-mail: samjjx@hotmail.com 
 * @version 创建时间：2015-8-25 下午6:27:57 
 * 类说明 
 */
public class LinkedListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * @Title: main
		 * @Description: TODO(这里用一句话描述这个方法的作用)
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
