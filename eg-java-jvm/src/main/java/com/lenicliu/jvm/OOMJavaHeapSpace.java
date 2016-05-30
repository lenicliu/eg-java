package com.lenicliu.jvm;

/**
 * -Xms16M -Xmx16M
 * 
 * @author lenicliu
 *
 */
public class OOMJavaHeapSpace {
	public static void main(String[] args) throws Exception {
		System.out.println(new byte[1024 * 1024 * 32]);
	}
}