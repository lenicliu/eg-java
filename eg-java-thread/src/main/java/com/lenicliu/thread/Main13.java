package com.lenicliu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main13 {

	public static void main(String[] args) throws InterruptedException {
	}

	public static class ReadWriteLockList {
		List<String>	list	= new ArrayList<>();
		ReadWriteLock	lock	= new ReentrantReadWriteLock();
		Lock			rlock	= lock.readLock();
		Lock			wlock	= lock.writeLock();

		public String get(int index) {
			rlock.lock();
			try {
				return list.get(index);
			} finally {
				rlock.unlock();
			}
		}

		public String remove(int index) {
			wlock.lock();
			try {
				return list.remove(index);
			} finally {
				wlock.unlock();
			}
		}
	}
}