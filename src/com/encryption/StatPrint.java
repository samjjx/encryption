package com.encryption;

import java.util.Scanner;

public class StatPrint {
	private static Scanner scan;
	/**
	 * Know the encryption performance of per-label. Print the 
	 * encryption performance of whole graph
	 * @param t0
	 * @param labelnumbers
	 */
	public static void printTime(double t0,int labelnumbers)
	{
		t0=t0*labelnumbers*2;
		t0/=1000;
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
		scan = new Scanner(System.in);
		while(scan.hasNext())
		{
			double t0=scan.nextDouble();
			int labelNumbers=scan.nextInt();
			printTime(t0, labelNumbers);
			System.out.println();
		}
	}
}
