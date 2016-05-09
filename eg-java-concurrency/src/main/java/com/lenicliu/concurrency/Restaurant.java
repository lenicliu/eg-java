package com.lenicliu.concurrency;

public class Restaurant {
	public static void main(String[] args) {
		Shared shared = new Shared();
		new Thread(new Producer(shared)).start();
		new Thread(new Comsumer(shared)).start();
	}
}

class Producer implements Runnable {
	private Shared shared;

	public Producer(Shared shared) {
		super();
		this.shared = shared;
	}

	@Override
	public void run() {
		String[] messages = "Hi I am a java engineer quit".split(" ");
		for (String message : messages) {
			synchronized (shared) {
				shared.set(message);
				System.out.println("Producer : " + message);
			}
		}
	}
}

class Comsumer implements Runnable {
	private Shared shared;

	public Comsumer(Shared shared) {
		super();
		this.shared = shared;
	}

	@Override
	public void run() {
		String message = "";
		while (!message.equals("quit")) {
			synchronized (shared) {
				message = shared.get();
				System.out.println("Comsumer : " + message);
			}
		}
	}
}

class Shared {
	private String	message;
	private volatile boolean	writable	= true;

	public Shared() {
	}

	public synchronized String get() {
		while (writable) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		writable = true;
		notify();
		return message;
	}

	public synchronized void set(String message) {
		while (!writable) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.message = message;
		writable = false;
		notify();
	}
}