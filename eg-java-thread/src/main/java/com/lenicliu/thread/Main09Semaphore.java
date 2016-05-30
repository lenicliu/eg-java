package com.lenicliu.thread;

import java.util.concurrent.Semaphore;

public class Main09Semaphore {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3, true);

		Runnable worker = () -> {
			try {
				semaphore.acquire();
				// do sth.
				Thread.sleep(1000);
				System.out.println(System.currentTimeMillis() + " working");
			} catch (InterruptedException ignore) {
			} finally {
				semaphore.release();
			}
		};

		new Thread(worker).start();
		new Thread(worker).start();
		new Thread(worker).start();
		// This one will wait for calling semaphore.release()
		new Thread(worker).start();
	}
}