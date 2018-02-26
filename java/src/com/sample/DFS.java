package com.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;

/** 
 * @author ������  E-mail: samjjx@hotmail.com 
 * @version ����ʱ�䣺2015-8-3 ����3:08:55 
 * ��˵�� 
 */
public class DFS {

	/**
	 * @param args
	 */
	HashMap<Integer, ArrayList<Integer>> bigGraph;
	int debug=0;
	int nodes;
	int[] color;      //Judge if the related node is visited. 0:White 1��Gray 2 Black
	int time=0;       //program time
	int[] ans;        // the ancestor. -1 : NIL
	int[] discover;   //first time see the node
	int[] finish;     //leave time
	int[] topoNumber; // stand for the level
	ArrayList<Integer> dfsOrder=new ArrayList<Integer>();
	public DFS(HashMap<Integer, ArrayList<Integer>> bigGraph,int nodes)
	{
		this.bigGraph=bigGraph;
		this.nodes=nodes;
		color=new int[nodes];
		ans=new int[nodes];
		discover=new int[nodes];
		finish=new int[nodes];
		topoNumber=new int[nodes];
	}
	public void traverse()
	{
		dfsOrder.clear();
		for(int i=0;i<nodes;i++)
		{
			color[i]=0;
			ans[i]=-1;
		}
		time=0;
		for(int start=0;start<nodes;start++)
		{
			if(color[start]==0)
				DFS_VISIT(start);
			dfsOrder.add(-1);
		}
	}
	public void traverseInverse()
	{
		dfsOrder.clear();
		for(int i=0;i<nodes;i++)
		{
			color[i]=0;
			ans[i]=-1;
		}
		time=0;
		int[] traOrder=traOrder();
		for(int i=0;i<nodes;i++)
			if(color[traOrder[i]]==0)
			{
				DFS_VISIT(traOrder[i]);
				dfsOrder.add(-1);
			}
			
	}
	public int[] traOrder()
	{
		int[] result=new int[nodes];
		for(int i=0;i<nodes;i++)
		{
			int max=finish[0];
			result[i]=0;
			for(int j=0;j<nodes;j++)
				if(max<finish[j])
				{
					max=finish[j];
					result[i]=j;
				}
			finish[result[i]]=-1;
		}
		return result;
	}
//	public void DFS_VISIT(int start)
//	{
//		dfsOrder.add(start);
//		color[start]=1;
//		time++;
//		discover[start]=time;
//		if(bigGraph.containsKey(start))
//			for(int v:bigGraph.get(start))
//			{
//				if(color[v]==0)
//				{
//					ans[v]=start;
//					DFS_VISIT(v);
//				}
//			}
//		color[start]=2;
//		time++;
//		finish[start]=time;
//	}
	public void DFS_VISIT(int start)
	{
		Stack<Integer> dfsStack=new Stack<Integer>();
		dfsStack.push(start);
		dfsOrder.add(start);
		color[start]=1;
		time++;
		discover[start]=time;
		l:while(!dfsStack.empty())
		{
			int topNode=dfsStack.peek();
			if(bigGraph.containsKey(topNode))
			{
				for(int v:bigGraph.get(topNode))
					if(color[v]==0)
					{
						ans[v]=topNode;
						dfsOrder.add(v);
						dfsStack.push(v);
						color[v]=1;
						time++;
						discover[v]=time;
						continue l;
					}
				color[topNode]=2;
				time++;
				finish[topNode]=time;
				dfsStack.pop();
			}
			else
			{
				color[topNode]=2;
				time++;
				finish[topNode]=time;
				dfsStack.pop();
			}
		}
	}
	public void topoNumber()
	{
		for(int i=0;i<nodes;i++)
			topoNumber[i]=1;
		int[] order=traOrder();
		for(int i=0;i<order.length;i++)
		{
			ArrayList<Integer> linkList=bigGraph.get(order[i]);
			for(int j=0;j<linkList.size();j++)
				if(topoNumber[linkList.get(j)]<(topoNumber[i]+1))
					topoNumber[linkList.get(j)]=topoNumber[i]+1;
		}
	}
	

}
