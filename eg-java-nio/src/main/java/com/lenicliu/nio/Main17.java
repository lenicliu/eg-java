package com.lenicliu.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main17 {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("gigabyte.dat"));
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 4);
		Future<Integer> read = channel.read(buffer, 0);
		long total = 0;
		while (read.get() > 0) {
			total += read.get();
			buffer.clear();// clear for preparing next reading

			// position means the read position of channel
			read = channel.read(buffer, total);
		}
		channel.close();
		System.out.println(total);
	}

	public static <T> void dump(Iterator<T> iterator) {
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public static <T> void dump(Iterable<T> iterable) {
		Iterator<T> it = iterable.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}