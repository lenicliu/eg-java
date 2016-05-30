package com.lenicliu.thread;

import java.util.concurrent.CyclicBarrier;

public class Main08CyclicBarrier {

	public static void main(String[] args) {
		final int n = 5;
		Runnable finish = () -> {
			System.out.println("Go...");
		};
		CyclicBarrier barrier = new CyclicBarrier(n, finish);
		Runnable worker = () -> {
			try {
				barrier.await();
				System.out.println(System.currentTimeMillis());
			} catch (Exception ignore) {
			}
		};
		System.out.println("Ready...");
		for (int i = 0; i < n; i++) {
			new Thread(worker).start();
		}
	}
}