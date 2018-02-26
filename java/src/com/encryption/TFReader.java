package com.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.xml.transform.Templates;

/**
 * @author ������ E-mail: samjjx@hotmail.com
 * @version ����ʱ�䣺2015-2-17 ����6:10:14 ��˵��
 */
public class TFReader {
	public ArrayList<Integer> tflabelSize = new ArrayList<Integer>();
	public ArrayList<ArrayList<Integer>> tfLabel = new ArrayList<ArrayList<Integer>>();

	/**
	 * Read the binary file of '*_tlstart' which contain the content of the TFlabels' sizes  
	 * @param filePath the Path of the binary file
	 * @throws IOException
	 */
	public void readLabelSize(String filePath) throws IOException {
		File file = new File(filePath);
		InputStream in = null;
		byte[] tempbyte = new byte[4];
		in = new FileInputStream(file);
		while (in.read(tempbyte) != -1) {
			tflabelSize.add(countSize(tempbyte));
		}
		in.close();
	}
	public int count(){
		HashSet<Integer> collect=new HashSet<Integer>();
		for(ArrayList<Integer> temp:tfLabel){
			collect.addAll(temp);
		}
		return collect.size();
	}
	/**
	 * Read the binary file of '*_TL' which contain the content of the TFlabel, if you want to execute
	 * this function correctively ,you should execute the readLabelSize first  
	 * @param filePath the Path of the binary file
	 * @throws IOException
	 */
	public void readLabel(String filePath) throws IOException {
		File file = new File(filePath);
		InputStream in = new FileInputStream(file);
		for (int i = 0; i < tflabelSize.size(); i++)
			readOneLabel(in, tflabelSize.get(i));
		in.close();
	}
	/**
	 * Read a label from an inputStream
	 * @param in: an inputStream
	 * @param size: the label's size 
	 * @throws IOException
	 */
	public void readOneLabel(InputStream in, int size) throws IOException {
		ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
		byte[] tempbyte = new byte[4];
		for (int i = 0; i < size; i++) {
			in.read(tempbyte);
			tempArrayList.add(countSize(tempbyte));
		}
		tfLabel.add(tempArrayList);
	}
	/**
	 * Transform 4 byte to be a related Integer
	 * @param tempbyte: 4 byte
	 * @return The Integer
	 */
	public int countSize(byte[] tempbyte) {
		int result = 0;
		for (int i = 0; i < tempbyte.length; i++) {
			result += (tempbyte[i] & 0xFF) << (8 * i);
		}
		return result;
	}
	/**
	 * Show all the label
	 */
	public void showLabel() {
		for (int i = 0; i < tfLabel.size(); i++) {
			ArrayList<Integer> tempArrayList = tfLabel.get(i);
			System.out.print(i + 1 + "\t");
			for (int j = 0; j < tempArrayList.size(); j++)
				System.out.print(tempArrayList.get(j) + "\t");
			System.out.println();
			//if(i==100)
			//return;

		}
	}
	/**
	 * Read the labels's sizes and the label, then put them all in two ArrayList, respectively
	 * @param files : filePath the Path of the binary file
	 * @throws IOException
	 */
	public void iniRead(String[] files) throws IOException {
		readLabelSize(files[0]);
		readLabel(files[1]);
	}
	public void center(TFReader tr)
	{
		ArrayList<Integer> totalArrayList=new ArrayList<Integer>();
		for(int i=0;i<tfLabel.size();i++)
		{
			ArrayList<Integer> temp=tfLabel.get(i);
			totalArrayList.addAll(temp);
		}
		ArrayList<Integer> resultArrayList=new ArrayList<Integer>();
		resultArrayList.add(100);
		while(!totalArrayList.isEmpty())
		{
			
		}
	}
	//tr��ɾ��������center�ı�ǩ
	public void check(TFReader tr) throws IOException
	{
		//trdelete��ԭʼ��ǩ������֮ǰ�Ĵ��룬ֱ�ӿ��������������պ÷��ˡ���Ӱ����룩
		TFReader trdelete=new TFReader();
		trdelete.readLabelSize("K:/python/peppy-master/_exp/index/newindex/G05/G05_tlstart");
		trdelete.readLabel("K:/python/peppy-master/_exp/index/newindex/G05/G05_TL");
		
//		l:for(int i=0;i<trdelete.tfLabel.size();i++)
//		{
//			int temp=0;
//			if(i<tfLabel.size()/2)
//				{
//					temp=i;
//					for(int j=0;j<trdelete.tfLabel.size();j++)
//					{
//						if(temp==trdelete.tfLabel.get(i).get(j))
//						{
//							trdelete.tfLabel.get(i).remove(j);
//							continue l;
//						}
//					}
//				}
//			else{
//				temp=i-tfLabel.size()/2;
//				for(int j=0;j<trdelete.tfLabel.size();j++)
//				{
//					if(temp==trdelete.tfLabel.get(i).get(j))
//					{
//						trdelete.tfLabel.get(i).remove(j);
//						continue l;
//					}
//				}
//			}
//		}
		Random random=new Random();
		boolean bef=false,aft=false;
		l:while(true)
		{
			//a b��ʾ�������һ�Ե���в�ѯ��
			int a=random.nextInt(trdelete.tfLabel.size()/2);
			int b=random.nextInt(trdelete.tfLabel.size()/2)+trdelete.tfLabel.size()/2;
			//�ų���������Ĳ�ѯ
			if(a==b-trdelete.tfLabel.size()/2)
				continue l;
//			a=7;
//			b=1283+trdelete.tfLabel.size()/2;
			//bef��ʾɾ�����Ƿ�ɴ� aft��ʾɾ��ǰ�Ƿ�ɴ�
			System.out.println("After removing:");
			bef=checkOne(a, b, tr);
			System.out.println(bef);
			
			System.out.println("Complete label:");
			aft=checkOne(a, b, trdelete);
			System.out.println(aft);
			if(bef!=aft)
			{
				System.out.println("v is:"+a+" u is: "+(b-trdelete.tfLabel.size()/2));
				System.out.println("Before delete "+bef);
				System.out.println("After delete "+aft);
				break;
			}
			else {
				//System.out.println("Correct");
			}
			if(bef==true)
				break;
			System.out.println("**************************************");
		}
	}
	public boolean checkOne(int a,int b, TFReader tr)
	{
		ArrayList<Integer> aList=tr.tfLabel.get(a);
		ArrayList<Integer> bList=tr.tfLabel.get(b);
		System.out.println(a+" is:"+aList);
		System.out.println(b-tr.tfLabel.size()/2+" is:"+bList);
		
		for(int i=0;i<aList.size();i++)
		{
			for(int j=0;j<bList.size();j++)
				{
					int tempa=aList.get(i);
					int tempb=bList.get(j);
					if(tempa==tempb)
						return true;
				}
		}
		
		return false;
	}
	public void tfr2PP(HashMap<Integer, ArrayList<Integer>> lin,HashMap<Integer, ArrayList<Integer>> lout)
	{
		int total=tfLabel.size()/2;
		for(int i=0;i<total;i++)
			lin.put(i, tfLabel.get(i));
		for(int i=total;i<tfLabel.size();i++)
			lout.put(i-total, tfLabel.get(i));
		
	}
	public void testDisPeipei()
	{
		ArrayList<Integer> disArrayList=new ArrayList<Integer>();
		
		for(int i=0;i<tfLabel.size();i++)
		{
			ArrayList<Integer> temp=tfLabel.get(i);
			l:for(Integer vertex:temp)
				if(vertex!=i&&vertex!=i-tfLabel.size()/2)
				{
					if(!disArrayList.contains(vertex))
						disArrayList.add(vertex);
				}
		}
		//System.out.println(disArrayList);
		System.out.println(disArrayList.size());
//		System.out.println(tfLabel.size()/2);
	}
	public void testDis()
	{
		ArrayList<Integer> disArrayList=new ArrayList<Integer>();
		//line 212-225��ѯlin 233-252��ѯlout �����ִ�������
		for(int i=0;i<tfLabel.size()/2;i++)
		{
			boolean mark=true;     //Ĭ�ϲ���distinct center������Ҫ�ӵ�disArrayList����
			for(int j=0;j<tfLabel.size();j++)
			{
				if(j==i||j==(i+tfLabel.size()/2))     //�����õ��lin��lout���ж�
					continue;
				ArrayList<Integer> temp=tfLabel.get(j);
				if(temp.contains(i))                 //������ڱ��label���г��֣�mark��Ϊfalse����ʾ�õ���distinct center
				{
					mark=false;
					break;
				}
			}
			
			if(mark)                                //��ѯ�Ƿ����disArrayList���У����û�У�����
				for(int j=0;j<tfLabel.get(i).size();j++)
					if(tfLabel.get(i).get(j)==i)
						tfLabel.get(i).remove(j);
				
		}
		for(int i=tfLabel.size()/2;i<tfLabel.size();i++)
		{
			boolean mark=true;
			for(int j=0;j<tfLabel.size();j++)
			{
				if(j==i||j==(i-tfLabel.size()/2))
					continue;
				ArrayList<Integer> temp=tfLabel.get(j);
				if(temp.contains(i-tfLabel.size()/2))
				{
					mark=false;
					break;
				}
			}
			
			if(mark)
				for(int j=0;j<tfLabel.get(i).size();j++)
					if(tfLabel.get(i).get(j)==(i-tfLabel.size()/2))
						tfLabel.get(i).remove(j);
		}
		
		for(int i=0;i<tfLabel.size()/2;i++)
		{
			ArrayList<Integer> temp=tfLabel.get(i);
			for(int j=0;j<temp.size();j++)
				if(!disArrayList.contains(temp.get(j)))
					disArrayList.add(temp.get(j));
		}
		System.out.println("the dis is :"+disArrayList.size());
		for(int i=tfLabel.size()/2;i<tfLabel.size();i++)
		{
			ArrayList<Integer> temp=tfLabel.get(i);
			for(int j=0;j<temp.size();j++)
				if(!disArrayList.contains(temp.get(j)))
					disArrayList.add(temp.get(j));
		}
		System.out.println("the dis is :"+disArrayList.size());
	}
	public static void main(String[] args) throws IOException {
		TFReader tr = new TFReader();
		tr.readLabelSize("K:/Java/data/dataset/livejournal_new_tlstart");
		tr.readLabel("K:/Java/data/dataset/livejournal_new_TL");
		tr.testDisPeipei();
		//		tr.showLabel();
		//tr.check(tr);
		//System.out.println(tr.checkOne(10,10,tr));
//		System.out.println(tr.tflabelSize.size());
	}

}
