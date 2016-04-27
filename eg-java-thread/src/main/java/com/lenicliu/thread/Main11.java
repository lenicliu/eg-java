package com.lenicliu.thread;

import java.util.stream.IntStream;

public class Main11 {

	public static void main(String[] args) {
		class SharedInt {
			int		value		= -1;
			boolean	available	= false;

			synchronized int get() {
				while (!this.available) {
					try {
						this.wait();
					} catch (InterruptedException e) {
					}
				}
				this.available = false;
				this.notify();
				return this.value;
			}

			synchronized void set(int value) {
				while (this.available) {
					try {
						this.wait();
					} catch (InterruptedException e) {
					}
				}
				this.value = value;
				this.available = true;
				this.notify();
			}
		}

		SharedInt shared = new SharedInt();

		Runnable producer = () -> {
			int[] values = IntStream.range(0, 10).toArray();
			for (int value : values) {
				synchronized (shared) {
					shared.set(value);
					System.out.println("producer : " + value);
				}
			}
		};

		Runnable consumer = () -> {
			while (true) {
				synchronized (shared) {
					int value = shared.get();
					System.out.println("consumer : " + value);
					if (value == 9) {
						break;
					}
				}
			}
		};

		new Thread(consumer).start();
		new Thread(producer).start();
	}
}