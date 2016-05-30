package com.lenicliu.jvm;

/**
 * -Xms32M -Xmx32M
 * 
 * @author lenicliu
 *
 */
public class OOMTooManyThread {
	public static void main(String[] args) {
		Runnable r = () -> {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		};
		while (true) {
			new Thread(r).start();
		}
	}
}