package com.encryption;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Jama.Matrix;

/** 
 * @author 蒋家鑫  E-mail: samjjx@hotmail.com 
 * @version 创建时间：2015-8-26 上午10:46:33 
 * 类说明 
 */
public class ASPE {

	/**
	 * @param args
	 */
	Matrix M,MT;
	double[][] label;
	int d,size;
	long time=0;
	/**
	 * 
	 * @param d : Dimension of the Matrix
	 * @param files : The path of the tflabel file
	 * @throws IOException
	 */
	public ASPE(int d,int center) 
	{
		this.d=d;
		M=Matrix.random(d, d);
		MT=M.inverse();
		size=(center*2/d+1)*d;
	}
	/**
	 * Encrypt one label
	 * @param label : The label to be encrypted 
	 * @return The label in the encrypted domain
	 */
	public double[] encryptOne(ArrayList<Integer> label)
	{
		//划分成几块
		int parts=size/d;
		Label tfLabel=new Label(label, size,parts);
		
		double[] res=tfLabel.labelMatrix.times(M).getRowPackedCopy();
		return res;
	}
	/**
	 * Test query
	 * @param a_label: The u label in encrypted domain
	 * @param b_label: The v label in encrypted domain
	 * @return  : True if u can reach v. False if u cannot reach v
	 */
	public boolean queryOnce(double[] a_label,double[] b_label)
	{
		double[] add=new double[size];
		for(int j=0;j<add.length;j++)
			add[j]=a_label[j]+b_label[j];

		long t0=System.currentTimeMillis();
		double[] q=encryptQ(size);
		boolean result=intersectionOperation(q, add);
		t0=System.currentTimeMillis()-t0;
		time+=t0;
		return result;
	}
	
/**
 * Test the correctness of the intersection
 * @param p : The vector of p ( The detail is in the CGBE paper)
 * @param q : The vector of q
 * @return : True if u reach v, False if u cannot reach v
 */
	public boolean intersectionOperation(double[] p,double[] q)
	{
		double result=0;
		for(int i=0;i<p.length;i++)
			result+=p[i]*q[i];
		//System.out.println(result);
		if(result==0.0)
			return false;
		else
			return true;
	}
	/**
	 * Encrypt q
	 * @param size of the q vector
	 * @return the encrypted q
	 */
	public double[] encryptQ(int size)
	{
		double[] constantLabel=new double[size];
		Random random=new Random();
		double r=random.nextDouble();
		for(int i=0;i<size;i++)
		{	
			if(i%2==0)
				constantLabel[i]=1;
			constantLabel[i]=constantLabel[i]*r;
		}
		int parts=size/d;
		double[] q;
		Matrix qLabel=new Matrix(constantLabel, parts);
		q=MT.times(qLabel.transpose()).getColumnPackedCopy();
		return q;
	}
	

}

class Label {

	/**
	 * @param args
	 */
	public Matrix labelMatrix;
	/**
	 * oriLabel: original label. which is a tf-label
	 * size: the total label size based on the scale of the dataset
	 * parts: the number of the divisions of the a label
	 */
	public Label(ArrayList<Integer> oriLabel,int size,int parts)
	{
		double[] label=new double[size];
		for(int i=0;i<oriLabel.size();i++)
		{
			int number=oriLabel.get(i);
			label[number*2+1]=1;
		}
		labelMatrix=new Matrix(label, parts);
	}
	

}
