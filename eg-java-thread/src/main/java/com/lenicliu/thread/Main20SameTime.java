package com.lenicliu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main20SameTime {
	private static final int n = 6;

	public static void main(String[] args) throws InterruptedException {
		start(n, new ObjectGate());
		start(n, new CountLatchGate());
		start(n, new CyclicBarrierGate(n));
	}

	static interface Gate {
		void ready();

		void go();
	}

	static class ObjectGate implements Gate {
		private final Object o = new Object();

		@Override
		public void ready() {
			synchronized (o) {
				try {
					o.wait();
				} catch (InterruptedException e) {
				}
			}
		}

		@Override
		public void go() {
			synchronized (o) {
				o.notifyAll();
			}
		}
	}

	static class CountLatchGate implements Gate {
		private final CountDownLatch latch = new CountDownLatch(1);

		@Override
		public void ready() {
			try {
				latch.await();
			} catch (InterruptedException e) {
			}
		}

		@Override
		public void go() {
			latch.countDown();
		}
	}

	static class CyclicBarrierGate implements Gate {
		private final CyclicBarrier barrier;

		CyclicBarrierGate(int n) {
			barrier = new CyclicBarrier(n);
		}

		@Override
		public void ready() {
			try {
				barrier.await();
			} catch (InterruptedException e) {
			} catch (BrokenBarrierException e) {
			}
		}

		@Override
		public void go() {
			// do nothing
		}
	}

	private static void start(final int n, final Gate gate) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			new Thread(() -> {
				try {
					gate.ready();
				} catch (Exception e) {
				}
				System.out.println(fix(gate.getClass().getSimpleName()) + "\t" + System.currentTimeMillis());
			}).start();
		}
		Thread.sleep(100);

		gate.go();
		Thread.sleep(100);
		System.out.println();
	}

	private static String fix(String value) {
		return value == null || value.length() < 10 ? value : value.substring(0, 10) + "...";
	}
}