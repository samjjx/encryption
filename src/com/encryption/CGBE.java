package com.encryption;

import java.math.BigInteger;
import java.util.Random;
/** 
 * @author 蒋家鑫  E-mail: samjjx@hotmail.com 
 * @version 创建时间：2015-2-15 下午2:33:33 
 * This Class is transformed from the C++ version of CGBE(author: Fan Zhe)
 * References: <br>
 * [1] Fan Z, Choi B, Chen Q, et al. Structure-Preserving Subgraph Query Services[J].
 *    URL: <a href="http://www.comp.hkbu.edu.hk/~bchoi/fanzhetkde15techreport.pdf">http://www.comp.hkbu.edu.hk/~bchoi/fanzhetkde15techreport.pdf</a><br>
 * 
 */
public class CGBE {
	public BigInteger encoding;
	public BigInteger I;
	public BigInteger r, g, x, gx, gx_1, n;
	public Random r_state;
	private static final int DEFAULTMSGSIZE = 2048;
	private static final int DEFAULTRANDOM = 32;

	public CGBE() {
		r_state = new Random();
		generator();
	}
	/**
	 * Encrypt a BigInteger with the CGBE algorithm. 
	 * @param m
	 * @return
	 */
	//c=m*r*g^x(mod n)
	public BigInteger encrypt(BigInteger m) {
		BigInteger c = m;
		r = genRand(DEFAULTRANDOM);
		c = m.multiply(r);
		c = _mulmod(c, gx, n);
		return c;
	}
	/**
	 * Decrypt a cipher 
	 * @param c : the cipher
	 * @return : the message * random number
	 */
	// m= c * g^-x (mod n)
	public BigInteger decrypt(BigInteger c) {
		return _mulmod(c, gx_1, n);
	}
	/**
	 * Set a BigInteger value
	 * @param _s
	 * @return
	 */
	public BigInteger setvalue(BigInteger _s) {
		return _s;
	}

	public BigInteger setvalue(int _s) {
		return new BigInteger("" + _s);
	}

	public BigInteger mul(BigInteger _l, BigInteger _r) {
		return _mulmod(_l, _r, n);
	}

	public BigInteger add(BigInteger _l, BigInteger _r) {
		return _addmod(_l, _r, n);
	}

	public boolean isZero(BigInteger _m) {
		if (_m.equals(new BigInteger("0")))
			return true;
		else
			return false;
	}

	private BigInteger genRand(int _size) {
		return new BigInteger(_size, r_state);
	}

	private BigInteger _mulmod(BigInteger a, BigInteger b, BigInteger mod) {
		return a.multiply(b).mod(mod);
	}

	private BigInteger _addmod(BigInteger a, BigInteger b, BigInteger mod) {
		return a.add(b).mod(mod);
	}

	private void generator() {
		encoding = new BigInteger("2147483647");

		n = new BigInteger(
				"16594900510468848618779859529048970562434151887982758146333908610126995606875959583150754096904939163058088732836622155984787148951754484085080055100022863834734805390568247562546896289033380553816126638204887528488669669650795595078243509793710242842807549471564349489475385605450605971121686746302940877979192119330073120037897310684720105742251875954769621467756551735273135476454843803189963924528556657594267637567873522001475725298702714900996470201476441839588209221083986823968709674978326806081379946096271872621299301620422089624262355737113475982440526827509351111547011077378815198009079208558801443913617");
		x = genRand(DEFAULTMSGSIZE);
		g = new BigInteger("3");
		gx = g.modPow(x, n);
		gx_1 = gx.modInverse(n);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CGBE intersection = new CGBE();
		BigInteger a, b;
		a = intersection.setvalue(new BigInteger("1"));
		b = intersection.setvalue(new BigInteger("1"));
		a = intersection.encrypt(a);
		b = intersection.encrypt(b);
		System.out.println("the message is:" + a);
		BigInteger cipher;
		cipher = intersection.encrypt(a);
		System.out.println("the cipher is:" + cipher);
		System.out.println("the orginal message is :"
				+ intersection.decrypt(cipher));

	}

}
