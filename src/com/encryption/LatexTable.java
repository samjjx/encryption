package com.encryption;

import java.util.Scanner;

public class LatexTable {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext())
		{
			for(int i=0;i<3;i++)
				System.out.print(scan.next()+"  &  ");
			System.out.println(scan.next()+"\\\\");
		}

	}

}
