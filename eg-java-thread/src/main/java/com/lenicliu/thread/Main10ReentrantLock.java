package com.lenicliu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main10ReentrantLock {

	public static void main(String[] args) {
		final ExecutorService threadPool = Executors.newFixedThreadPool(3);
		ReentrantLock lock = new ReentrantLock();
		Runnable worker = () -> {
			lock.lock();
			// lock.lock();
			try {
				TimeUnit.SECONDS.sleep(2);// do sth.
				System.out.println(System.currentTimeMillis() + " working");
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
			}
		};

		threadPool.submit(worker);
		threadPool.submit(worker);
		threadPool.submit(worker);

		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}