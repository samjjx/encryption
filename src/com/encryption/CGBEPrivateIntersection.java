package com.encryption;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author ������ E-mail: samjjx@hotmail.com
 * @version ����ʱ�䣺2015-2-19 ����8:13:38 ��˵��
 */
public class CGBEPrivateIntersection {
	private static final int p = 2147483647;
	CGBE cgbe;
	final BigInteger n = new BigInteger(
			"16594900510468848618779859529048970562434151887982758146333908610126995606875959583150754096904939163058088732836622155984787148951754484085080055100022863834734805390568247562546896289033380553816126638204887528488669669650795595078243509793710242842807549471564349489475385605450605971121686746302940877979192119330073120037897310684720105742251875954769621467756551735273135476454843803189963924528556657594267637567873522001475725298702714900996470201476441839588209221083986823968709674978326806081379946096271872621299301620422089624262355737113475982440526827509351111547011077378815198009079208558801443913617");
	int[] centers = {(int) 1e3, (int) 1e4, (int) 1e5, (int) 1e6};
	public CGBEPrivateIntersection() {
		cgbe = new CGBE();
	}
	/**
	 * Encrypt a label
	 * @param label : a label which is an array
	 * @param C : the total number of the nodes in the graph
	 * @return : the ciphers of the label
	 */
	public BigInteger[] encrypt(final int[] label, final int C) {
		BigInteger[] cipherBigIntegers = new BigInteger[C];	
		for (int i = 0; i < cipherBigIntegers.length; i++)
			cipherBigIntegers[i] = cgbe
					.encrypt(new BigInteger("" + p));
		for (int i = 0; i < label.length; i++)
			cipherBigIntegers[label[i]] = cgbe.encrypt(BigInteger.ONE);
		return cipherBigIntegers;
	}
	/**
	 * Judge the reachable ability
	 * @param ret : the cipher of the intersection
	 * @return : true if it is reachable, otherwise false
	 */
	public boolean decrypt(BigInteger ret) {
		if (cgbe.decrypt(cgbe.decrypt(ret)).mod(new BigInteger("" + p)).equals(BigInteger.ZERO))
			return false;
		return true;
	}
	/**
	 * Make the intersection in the encrypted domain
	 * @param lin : the lin label
	 * @param lout : the lout label
	 * @return : the intersection result
	 */
	public BigInteger intersection(final BigInteger[] lin,
			final BigInteger[] lout) {
		BigInteger resultBigInteger = BigInteger.ZERO;
		for (int i = 0; i < lin.length; i++)
			resultBigInteger = resultBigInteger.add(lin[i].multiply(lout[i]));
		return resultBigInteger;
	}
	/**
	 * Transform a ArrayList to be an array
	 * @param tempArrayList : the ArrayList
	 * @return : the array
	 */
	public int[] toarray(ArrayList<Integer> tempArrayList)
	{
		int[] result=new int[tempArrayList.size()];
		for(int i=0;i<tempArrayList.size();i++)
			result[i]=tempArrayList.get(i);
		return result;
	}
	
	
}
