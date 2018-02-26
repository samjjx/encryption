package com.printinfo;

import java.util.Scanner;

public class VGSQUARE {
	final static long VK=1000;
	final static long VM=1000000;
	final static long VG=1000000000;
	public static void print(long V)
	{
		if(V>VK&&V<=VM)
		{
			System.out.format("%.2fK",(double)V/VK);
			return ;
		}
		if(V>VM&&V<=VG)
		{
			System.out.format("%.2fM",(double)V/VM);
			return ;
		}
		if(V>VG)
		{
			System.out.format("%.2fG",(double)V/VG);
			return ;
		}
	}
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext())
		{
			long V=scan.nextLong();
			V=V*V;
			print(V);
			System.out.println();
		}
	}

}
