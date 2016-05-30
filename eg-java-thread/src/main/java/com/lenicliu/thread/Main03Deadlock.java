package com.lenicliu.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Part 1 - Thread APIs <br/>
 * Chapter 2 : Deadlock Simple<br/>
 * 
 * @author lenicliu
 *
 */
public class Main03Deadlock {

	public static void main(String[] args) throws Exception {
		DeadLock deadLock = new DeadLock();

		Thread t1 = new Thread(() -> deadLock.method1(), "DLK-1");
		Thread t2 = new Thread(() -> deadLock.method2(), "DLK-2");

		t1.start();
		t2.start();

		while (true) {
			System.out.println(Helper.format(t1));
			System.out.println(Helper.format(t2));
			Helper.process(1000);
		}
	}

	static class DeadLock {

		private final Logger	logger	= LoggerFactory.getLogger(getClass());
		private final Object	LOCK1	= new Object();
		private final Object	LOCK2	= new Object();

		public DeadLock() {
			super();
		}

		public void method1() {
			synchronized (LOCK1) {
				logger.info("LOCK1 in Method1 Begin");
				Helper.process(10);
				synchronized (LOCK2) {
					logger.info("LOCK2 in Method1 Begin");
					Helper.process(10);
					logger.info("LOCK2 in Method1 End");
				}
				logger.info("LOCK1 in Method1 End");
			}
		}

		public void method2() {
			synchronized (LOCK2) {
				logger.info("LOCK2 in Method2 Begin");
				Helper.process(10);
				synchronized (LOCK1) {
					logger.info("LOCK1 in Method2 Begin");
					Helper.process(10);
					logger.info("LOCK1 in Method2 End");
				}
				logger.info("LOCK2 in Method2 End");
			}
		}
	}
}