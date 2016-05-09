package com.lenicliu.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main18 {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		Path path = Paths.get("gigabyte.dat");
		System.gc();
		System.out.println(single(path));
		TimeUnit.SECONDS.sleep(3);
		System.gc();
		System.out.println(multi(path));
	}

	private static long single(Path path) throws InterruptedException, ExecutionException, IOException {
		long begin = System.currentTimeMillis();
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
		Future<Integer> read = channel.read(buffer(), 0);
		long total = 0;
		while (read.get() > 0) {
			total += read.get();
			// position means the read position of channel
			read = channel.read(buffer(), total);
		}
		channel.close();
		long end = System.currentTimeMillis();
		System.out.println("single:" + (end - begin));
		return total;
	}

	private static long multi(Path path) throws IOException, InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		long begin = System.currentTimeMillis();
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
		Read read = new Read();
		for (long position = 0; position < channel.size(); position += 1024 * 1024 * 4) {
			final long p = position;
			exec.execute(() -> read.read(channel.read(buffer(), p)));
		}
		exec.shutdown();
		exec.awaitTermination(10, TimeUnit.MINUTES);
		channel.close();
		long end = System.currentTimeMillis();
		System.out.println("multi:" + (end - begin));
		return read.getValue();
	}

	private static ByteBuffer buffer() {
		return ByteBuffer.allocate(1024 * 1024 * 4);
	}
}

class Read {
	private long	total;
	private Object	LOCK	= new Object();

	public void read(Future<Integer> future) {
		Integer read = 0;
		try {
			read = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		synchronized (LOCK) {
			total += read;
		}
	}

	public long getValue() {
		synchronized (LOCK) {
			return total;
		}
	}
}