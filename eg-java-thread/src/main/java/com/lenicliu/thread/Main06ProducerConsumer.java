package com.lenicliu.thread;

public class Main06ProducerConsumer {
	public static void main(String[] args) {
		Shared shared = new Shared();
		new Thread(new Producer(shared)).start();
		new Thread(new Consumer(shared)).start();
	}

	static class Producer implements Runnable {
		private Shared shared;

		public Producer(Shared shared) {
			super();
			this.shared = shared;
		}

		@Override
		public void run() {
			for (char c = 'a'; c <= 'z'; c++) {
				synchronized (shared) {
					shared.setC(c);
					System.out.println("Produce :" + c);
				}
			}
		}
	}

	static class Consumer implements Runnable {
		private Shared shared;

		public Consumer(Shared shared) {
			super();
			this.shared = shared;
		}

		@Override
		public void run() {
			char c = '.';
			while (c < 'z') {
				synchronized (shared) {
					c = shared.getC();
					System.out.println("Consume :" + c);
				}
			}
		}
	}

	static class Shared {
		private char	c;
		private boolean	writeable	= true;

		synchronized char getC() {
			while (writeable) {
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
			}
			writeable = true;
			this.notify();
			return c;
		}

		synchronized void setC(char c) {
			while (!writeable) {
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
			}
			this.c = c;
			writeable = false;
			notify();
		}
	}
}