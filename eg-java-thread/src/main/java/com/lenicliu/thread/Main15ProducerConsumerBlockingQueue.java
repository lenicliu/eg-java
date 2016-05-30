package com.lenicliu.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Main15ProducerConsumerBlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

		Runnable producer = () -> {
			int[] values = IntStream.range(0, 10).toArray();
			for (int value : values) {
				try {
					System.out.println("producer:" + value);
					queue.put(value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("producer: done");
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
			System.out.println("consumer: done");
		};

		new Thread(consumer).start();
		new Thread(producer).start();
	}
}
