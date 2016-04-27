package com.lenicliu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Main12 {

	public static void main(String[] args) {
		class SharedInt {
			int			value		= -1;
			boolean		available	= false;
			Lock		lock		= new ReentrantLock();
			Condition	condition	= lock.newCondition();

			void lock() {
				lock.lock();
			}

			void unlock() {
				lock.unlock();
			}

			int get() {
				lock.lock();
				try {
					while (!this.available) {
						try {
							this.condition.await();
						} catch (InterruptedException e) {}
					}
					this.available = false;
					this.condition.signal();
					return this.value;
				} finally {
					lock.unlock();
				}
			}

			void set(int value) {
				lock.lock();
				try {
					while (this.available) {
						try {
							this.condition.await();
						} catch (InterruptedException e) {}
					}
					this.value = value;
					this.available = true;
					this.condition.signal();
				} finally {
					lock.unlock();
				}
			}
		}

		SharedInt shared = new SharedInt();

		Runnable producer = () -> {
			int[] values = IntStream.range(0, 10).toArray();
			for (int value : values) {
				shared.lock();
				shared.set(value);
				System.out.println("producer : " + value);
				shared.unlock();
			}
		};

		Runnable consumer = () -> {
			int value = -1;
			while (value != 9) {
				shared.lock();
				value = shared.get();
				System.out.println("consumer : " + value);
				shared.unlock();
			}
		};

		new Thread(consumer).start();
		new Thread(producer).start();
	}
}