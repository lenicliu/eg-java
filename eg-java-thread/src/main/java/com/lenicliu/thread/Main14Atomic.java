package com.lenicliu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main14Atomic {
	public static void main(String[] args) throws InterruptedException {
		class Counter {
			private AtomicInteger count = new AtomicInteger(0);

			public int get() {
				return count.getAndIncrement();
			}
		}

		Counter counter = new Counter();

		Runnable runnable = () -> System.out.println(counter.get());

		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		
		threadPool.execute(runnable);
		threadPool.execute(runnable);
		threadPool.execute(runnable);
		threadPool.execute(runnable);
		threadPool.execute(runnable);
		threadPool.execute(runnable);
		
		threadPool.shutdown();
		threadPool.awaitTermination(5, TimeUnit.SECONDS);		
	}
}
