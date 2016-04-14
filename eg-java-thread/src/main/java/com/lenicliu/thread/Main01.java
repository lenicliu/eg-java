package com.lenicliu.thread;

/**
 * Part 1 - Thread APIs <br/>
 * Chapter 1 : Threads and Runnables <br/>
 * 
 * @author lenicliu
 *
 */
public class Main01 {
	public static void main(String[] args) throws Exception {
		// 1 new runnable
		Runnable r = () -> {
			// 5.1 implement runnable
			System.out.println("Hello Thread.");

			// 7.2 thread interrupt
			Thread t = Thread.currentThread();
			int count = 1;
			while (!t.isInterrupted()) {
				System.out.println(Helper.format(t) + ", count=" + count++);
			}
		};

		// 2 new thread
		Thread t = new Thread(r);
		System.out.println(Helper.format(t));

		// 3 thread name
		t.setName("Desk Thread");
		System.out.println(Helper.format(t));

		// 4 thread priority
		t.setPriority(Thread.MAX_PRIORITY);
		System.out.println(Helper.format(t));

		// 5.2 thread start
		t.start();
		System.out.println(Helper.format(t));

		// 7.1 thread interrupt
		while (Math.abs(Math.random()) > 1) {
		}
		t.interrupt();
		System.out.println(Helper.format(t));

		// 6 thread join
		t.join();
		System.out.println(Helper.format(t));

		// 8 thread sleep
		System.out.println(Helper.format(Thread.currentThread()) + ", sleep begin");
		Thread.sleep(1000);
		System.out.println(Helper.format(Thread.currentThread()) + ", sleep end");
	}
}