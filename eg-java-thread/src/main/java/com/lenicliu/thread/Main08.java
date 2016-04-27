package com.lenicliu.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Main08 {

	public static void main(String[] args) {
		Runnable finish = () -> {
			System.out.println("S:" + System.currentTimeMillis());
		};
		CyclicBarrier barrier = new CyclicBarrier(3, finish);
		Runnable worker = () -> {
			try {
				TimeUnit.SECONDS.sleep(2);// do sth
				barrier.await();
			} catch (Exception ignore) {
			}
		};
		System.out.println("E:" + System.currentTimeMillis());
		new Thread(worker).start();
		new Thread(worker).start();
		new Thread(worker).start();
	}
}