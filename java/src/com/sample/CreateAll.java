package com.sample;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/** 
 * @author ������  E-mail: samjjx@hotmail.com 
 * @version ����ʱ�䣺2015-8-6 ����5:31:14 
 * ��˵�� 
 */
public class CreateAll {

	/**
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		ArrayList<String> dataList=new ArrayList<String>();
//		dataList.add("dataset/test.txt");
//		dataList.add("dataset/soc-Epinions1.txt");
//		dataList.add("dataset/wikitalk.txt");
//		dataList.add("dataset/slashdot0902.txt");
		
//		dataList.add("dataset/p2p-Gnutella04.txt");
//		dataList.add("dataset/p2p-Gnutella05.txt");
//		dataList.add("dataset/p2p-Gnutella06.txt");
//		dataList.add("dataset/p2p-Gnutella08.txt");
//		dataList.add("dataset/p2p-Gnutella09.txt");
//		dataList.add("dataset/p2p-Gnutella24.txt");
//		dataList.add("dataset/p2p-Gnutella25.txt");
//		dataList.add("dataset/p2p-Gnutella30.txt");
//		dataList.add("dataset/p2p-Gnutella31.txt");
//		dataList.add("dataset/Wiki-Vote.txt");
//		dataList.add("dataset/Amazon0302.txt");
//		dataList.add("dataset/Cit-HepPh.txt");
//		dataList.add("dataset/Cit-HepTh.txt");
//		dataList.add("dataset/web-BerkStan.txt");
//		dataList.add("dataset/soc-Epinions1.txt");
//		dataList.add("dataset/Email-EuAll.txt");
//		dataList.add("dataset/cit-Patents.txt");
//		dataList.add("dataset/web-BerkStan.txt");
//		dataList.add("dataset/web-Google.txt");
//		dataList.add("dataset/web-NotreDame.txt");
//		dataList.add("dataset/Amazon0312.txt");
//		dataList.add("dataset/Amazon0505.txt");
//		dataList.add("dataset/Amazon0601.txt");
//		dataList.add("dataset/soc-sign-Slashdot081106.txt");
//		dataList.add("dataset/soc-sign-Slashdot090216.txt");
//		dataList.add("dataset/soc-sign-Slashdot090221.txt");
//		dataList.add("dataset/soc-Epinions1.txt");
//		dataList.add("dataset/Slashdot0902.txt");
//		dataList.add("dataset/twitter_combined.txt");
//		dataList.add("dataset/web-Stanford.txt");
		dataList.add(args[0]);
		String sccPath=args[1];
		PrintStream out = System.out;
		for(String dataSet:dataList)
		{
			SnapReader sr=new SnapReader(dataSet);
			HashMap<Integer, ArrayList<Integer>> temp=sr.format(sr.bigGraph);
			long t0=System.currentTimeMillis();
			temp=sr.eliminateSCC(temp);
		
			System.out.println("Scc costs: "+ (System.currentTimeMillis()-t0));
			long t_Total=System.currentTimeMillis();
//			sr.storeScc(temp, sccPath);
			System.setOut(out);
			System.out.println(dataSet+" Scc eliminated");
			System.out.println(temp.size());
			PPTopo pptlable=new PPTopo();
			t0=System.currentTimeMillis();
			System.out.println("Begin processing");
			HashMap<Integer, ArrayList<Integer>>[] label=pptlable.CreatePPTopoBrFS(temp);
			System.out.println("End processing");
			System.out.println("Cont:"+(System.currentTimeMillis()-t0)+"ms");
			System.out.println("Total cost: "+(System.currentTimeMillis()-t_Total));
			System.out.println("Begin storing");
			sr.storeLabel(label[0], sr.labelPath,"in");
			sr.storeLabel(label[1], sr.labelPath,"out");
			System.setOut(out);
			System.out.println("Finish storing");
			
			System.out.println(dataSet+" finished");
		}
	}

}
