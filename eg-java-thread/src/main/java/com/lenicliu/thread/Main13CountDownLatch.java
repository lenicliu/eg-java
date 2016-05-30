package com.lenicliu.thread;

import java.util.concurrent.CountDownLatch;

public class Main13CountDownLatch {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Runnable r = () -> {
			try {
				latch.await();
			} catch (Exception e) {
			}
			System.out.println(System.currentTimeMillis());
		};
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		latch.countDown();
	}
}