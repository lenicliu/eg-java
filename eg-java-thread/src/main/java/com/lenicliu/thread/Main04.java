package com.lenicliu.thread;

/**
 * Part 1 - Thread APIs <br/>
 * Chapter 2 : Deadlock Complex<br/>
 * 
 * @author lenicliu
 *
 */
public class Main04 {

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(() -> DeadLockA.methodA1());
		Thread t2 = new Thread(() -> DeadLockC.methodC1());
		Thread t3 = new Thread(() -> DeadLockB.methodB1());
		t1.start();
		t2.start();
		t3.start();
		
		while (true) {
			System.out.println(Helper.format(t1));
			System.out.println(Helper.format(t2));
			System.out.println(Helper.format(t3));
			Helper.process(1000);
		}
	}

	static class DeadLockA {
		static synchronized void methodA1() {
			Helper.process(10);
			DeadLockB.methodB1();
		}
	}

	static class DeadLockB {
		synchronized static void methodB1() {
			Helper.process(10);
			DeadLockC.methodC1();
		}
	}

	static class DeadLockC {
		synchronized static void methodC1() {
			Helper.process(10);
			DeadLockA.methodA1();
		}
	}
}