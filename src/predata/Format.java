package predata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * @author ������  E-mail: samjjx@hotmail.com 
 * @version ����ʱ�䣺2015-2-23 ����10:29:01 
 * ��˵�� 
 */
public class Format {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void format(String inputPath,String outputPath) throws IOException
	{
		Scanner scan=new Scanner(new BufferedReader(new FileReader(inputPath)));
		int a=scan.nextInt();
		int b=scan.nextInt();
		System.out.println(a);
		System.out.println(b);
		PrintStream out=new PrintStream(outputPath);
		System.setOut(out);
		System.out.print(a);
		System.out.print(" ");
		System.out.println(b);
		ArrayList<Integer> arrayList=new ArrayList<Integer>();
		arrayList.add(scan.nextInt());
		arrayList.add(1);
		arrayList.add(scan.nextInt());
		for(int i=0;i<b-1;i++)
		{
			int temp=scan.nextInt();
			if(temp==arrayList.get(0))
			{
				arrayList.add(1,arrayList.remove(1)+1);
				arrayList.add(scan.nextInt());
			}
			else {
				for(int j=0;j<arrayList.size();j++)
					System.out.print(arrayList.get(j)+" ");
				System.out.println();
				arrayList.clear();
				arrayList.add(temp);
				arrayList.add(1);
				arrayList.add(scan.nextInt());
			}
		}
		for(int j=0;j<arrayList.size();j++)
			System.out.print(arrayList.get(j)+" ");
		System.out.println();
		arrayList.clear();
			
	}
	public static void main(String[] args) throws IOException {
//		format("K:/Javaweb/privacy_preserving/data/Slashdot0902.txt", "K:/Javaweb/privacy_preserving/data/Slashdot0902");
//		format("K:/Javaweb/privacy_preserving/data/soc-Epinions1.txt", "K:/Javaweb/privacy_preserving/data/soc-Epinions1");
//		format("K:/Javaweb/privacy_preserving/data/twitter_combined.txt", "K:/Javaweb/privacy_preserving/data/twitter_combined");
		format("K:/Java/data/dataset/soc-LiveJournal.txt", "K:/Java/data/dataset/soc-LiveJournal");
//		format("K:/Javaweb/privacy_preserving/data/p2p-Gnutella06.txt", "K:/Javaweb/privacy_preserving/data/p2p-Gnutella06");
//		format("K:/Javaweb/privacy_preserving/data/p2p-Gnutella08.txt", "K:/Javaweb/privacy_preserving/data/p2p-Gnutella08");
//		format("K:/Javaweb/privacy_preserving/data/p2p-Gnutella09.txt", "K:/Javaweb/privacy_preserving/data/p2p-Gnutella09");
//		format("K:/Javaweb/privacy_preserving/data/p2p-Gnutella24.txt", "K:/Javaweb/privacy_preserving/data/p2p-Gnutella24");
//		format("K:/Javaweb/privacy_preserving/data/p2p-Gnutella25.txt", "K:/Javaweb/privacy_preserving/data/p2p-Gnutella25");
//		format("K:/Javaweb/privacy_preserving/data/p2p-Gnutella30.txt", "K:/Javaweb/privacy_preserving/data/p2p-Gnutella30");
//		format("K:/Javaweb/privacy_preserving/data/p2p-Gnutella31.txt", "K:/Javaweb/privacy_preserving/data/p2p-Gnutella31");
//		format("K:/Javaweb/privacy_preserving/data/web-BerkStan.txt", "K:/Javaweb/privacy_preserving/data/web-BerkStan");
//		format("K:/Javaweb/privacy_preserving/data/web-NotreDame.txt", "K:/Javaweb/privacy_preserving/data/web-NotreDame");
//		format("K:/Javaweb/privacy_preserving/data/Wiki-Vote.txt", "K:/Javaweb/privacy_preserving/data/Wiki-Vote");
		
		
	}

}
