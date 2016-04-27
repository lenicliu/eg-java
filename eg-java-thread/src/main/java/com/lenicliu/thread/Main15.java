package com.lenicliu.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Main15 {
	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

		Runnable producer = () -> {
			int[] values = IntStream.range(0, 10).toArray();
			for (int value : values) {
				try {
					synchronized (queue) {
						queue.put(value);
						System.out.println("producer:" + value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Runnable consumer = () -> {
			int value = -1;
			while (value != 9) {
				try {
					value = queue.take();
					System.out.println("consumer:" + value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		new Thread(consumer).start();
		new Thread(producer).start();
	}
}
