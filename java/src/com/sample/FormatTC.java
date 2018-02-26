package com.sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FormatTC {

	/**
	 * output a dataset formatted in a file 
	 * @param formatDataSet : the dataset which is under the TC formattion
	 * @param outPutPath : the out Path
	 * @throws FileNotFoundException 
	 */
	public static void formatOut(HashMap<Integer, ArrayList<Integer>> formatDataSet, String outPutPath) throws FileNotFoundException
	{
		PrintStream psPrintStream=new PrintStream(outPutPath);
		System.setOut(psPrintStream);
		Set<Integer> keyset=formatDataSet.keySet();
		int node=0;
		int edge=0;
		for(int key:keyset)
			edge+=formatDataSet.get(key).size();
		ArrayList<Integer> nodeArrayList=new ArrayList<Integer>();
		for(int key:keyset)
		{
			if(!nodeArrayList.contains(key))
				nodeArrayList.add(key);
			ArrayList<Integer> tempArrayList=formatDataSet.get(key);
			for(int vertex:tempArrayList)
				if(!nodeArrayList.contains(vertex))
					nodeArrayList.add(vertex);
		}
		node=nodeArrayList.size();
		System.out.println(node);
		System.out.println(edge);
		for(int key:keyset)
		{
			ArrayList<Integer> tempArrayList=formatDataSet.get(key);
			for(int vertex:tempArrayList)
				System.out.println(key+" "+vertex);
		}
	}
	/**
	 * Pack the dataset's Input path
	 * @param path : the dataset's original name. like "Amazon0302"
	 * @return : The Input path of dataset. like "dataset/Amazon0302.txt"
	 */
	public static String packPath(String path)
	{
		return "dataset/"+path+".txt";
	}
	/**
	 * Pack the dataset's Output path
	 * @param path : the dataset's original name. like "Amazon0302"
	 * @return : The OutPut path of dataset. like "formatdataset/Amazon0302.g.u"
	 */
	public static String outPutPath(String path)
	{
		return "formatdataset/"+path+".g.u";
	}
	public static void main(String[] args) throws IOException {
		ArrayList<String> datasets=new ArrayList<String>();
		datasets.add("test");
//		datasets.add("Amazon0302");
//		datasets.add("p2p-Gnutella04");
//		datasets.add("p2p-Gnutella05");
//		datasets.add("p2p-Gnutella06");
//		datasets.add("p2p-Gnutella08");
//		datasets.add("p2p-Gnutella09");
//		datasets.add("p2p-Gnutella24");
//		datasets.add("p2p-Gnutella25");
//		datasets.add("p2p-Gnutella30");
//		datasets.add("p2p-Gnutella31");
//		datasets.add("Email-EuAll");
		datasets.add("Wiki-Vote");
		datasets.add("Cit-HepPh");
		datasets.add("Amazon0302");
		
		
		for(String dataset:datasets)
		{
			SnapReader srReader=new SnapReader(packPath(dataset));
			HashMap<Integer, ArrayList<Integer>> temp=srReader.format(srReader.bigGraph);
			temp=srReader.eliminateSCC(temp);
			temp=srReader.format(temp);
			formatOut(temp, outPutPath(dataset));
		}
	}

}
