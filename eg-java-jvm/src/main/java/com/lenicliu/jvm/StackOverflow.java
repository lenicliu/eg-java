package com.lenicliu.jvm;

/**
 * -Xss256K  1600+<br/>
 * -Xss512K  5600+<br/>
 * -Xss1024K 16000+<br/>
 * 
 * @author lenicliu
 *
 */
public class StackOverflow {

	public static void add(int i) {
		try {
			add(++i);
		} catch (Throwable e) {
			System.out.println(i);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		add(1);
	}
}