package com.lenicliu.thread;

public class Main20 {

	public static void main(String[] args) throws InterruptedException {
		Object o = new Object();
		Runnable r = () -> {
			synchronized (o) {
				try {
					o.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(System.currentTimeMillis());
		};

		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();

		Thread.sleep(100);
		synchronized (o) {
			o.notifyAll();
		}
	}
}