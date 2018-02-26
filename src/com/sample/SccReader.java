package com.sample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SccReader {

	public static void main(String[] args) throws IOException {
		FileInputStream in=new FileInputStream("K:/Java/data/dataset/scclivejournal");
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String tempLine;
		HashMap<Integer, ArrayList<Integer>> scc=new HashMap<Integer, ArrayList<Integer>>();
		while((tempLine=br.readLine())!=null){
			String temp[]=tempLine.split(" ");
			int v = Integer.parseInt(temp[0]);
			ArrayList<Integer> list=new ArrayList<Integer>();
			for(int i=1;i<temp.length;i++){
				list.add(Integer.parseInt(temp[i]));
			}
			scc.put(v, list);
		}
		int nodes=scc.size();
		int edges=0;
		for(int i=0;i<nodes;i++){
			edges+=scc.get(i).size();
		}
		PrintStream out = new PrintStream("K:/Java/data/dataset/livejournalTFInput");  
		System.setOut(out);
		System.out.println(nodes+" "+edges);
		for(int i=0;i<nodes;i++){
			if(scc.get(i).size()==0)
				continue;
			System.out.print(i+" ");
			System.out.print(scc.get(i).size()+" ");
			for(int j=0;j<scc.get(i).size();j++){
				if(j!=scc.get(i).size()-1){
					System.out.print(scc.get(i).get(j)+" ");
				}
				else {
					System.out.print(scc.get(i).get(j));
				}
			}
			System.out.println();
		}
	}

}
