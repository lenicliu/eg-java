package com.lenicliu.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main05 {

	private static Logger logger = LoggerFactory.getLogger("main");

	static boolean condition = false;
	static Object o = new Object();
	public static void main(String[] args) {
		class T1 extends Thread {
			@Override
			public void run() {
				synchronized (o) {
					logger.info("Wait Begin");
					while (!condition) {
						try {
							o.wait();
						} catch (InterruptedException e) {
						}
					}
					logger.info("Wait End");
				}
			}
		}

		T1 t1 = new T1();
		t1.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		synchronized (o) {
			condition = true;
			o.notify();
		}
	}
}
