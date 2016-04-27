package com.lenicliu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main18 {

	private static Logger LOG = LoggerFactory.getLogger(Main18.class);

	public static void main(String[] args) throws InterruptedException {
		class ReadWriteLockList {
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
		
		ReadWriteLockList list = new ReadWriteLockList();

		Runnable reader = () -> {
			System.out.println(list.get(0));
		};

		Runnable writer = () -> {
			list.remove(0);
		};

		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		threadPool.execute(reader);
		threadPool.execute(writer);
		threadPool.execute(reader);
		threadPool.execute(reader);
		threadPool.execute(writer);
		threadPool.execute(reader);
		threadPool.execute(reader);
		threadPool.execute(reader);
		threadPool.execute(writer);
		threadPool.execute(reader);
		threadPool.execute(reader);
		threadPool.execute(reader);
		threadPool.shutdown();
		threadPool.awaitTermination(10, TimeUnit.SECONDS);
	}
}