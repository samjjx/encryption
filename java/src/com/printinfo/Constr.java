package com.printinfo;

import java.util.Scanner;

public class Constr {
	
	public static void printTime(double t0)
	{
		if(t0<60)
		{
			System.out.format("%.2f"+"s",t0);
			return ;
		}
		int temp=(int)t0;
		int s=temp%60;
		temp/=60;
		if(temp<60)
		{
			System.out.print(temp+"min"+s+"s");
			return ;
		}
		int min=temp%60;
		temp/=60;
		System.out.format(temp+"hours"+min+"min");
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext())
		{
			double t0=scan.nextDouble();
			printTime(t0);
			System.out.println();
		}

	}

}
