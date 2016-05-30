package com.lenicliu.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main16ConcurrentHashMap {
	public static void main(String[] args) throws InterruptedException {
		Map<Integer, Integer> map = new ConcurrentHashMap<>();
		final int SIZE = 100;
		Runnable producer = () -> {
			for (int i = 0; i < SIZE; i++) {
				map.put(i, i + i);
			}
		};

		Runnable consumer = () -> {
			for (int i = 0; i < SIZE; i++) {
				map.remove(i);
			}
		};

		ExecutorService threadPool = Executors.newFixedThreadPool(20);

		for (int i = 0; i < 100; i++) {
			threadPool.execute(consumer);
			threadPool.execute(producer);
		}

		threadPool.shutdown();
		threadPool.awaitTermination(1, TimeUnit.MINUTES);
	}
}
