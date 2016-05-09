package com.lenicliu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main19 {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		CountDownLatch ready = new CountDownLatch(1);
		exec.execute(() -> {
			try {
				AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
				server.bind(new InetSocketAddress(10000));
				ready.countDown();
				AsynchronousSocketChannel socket = server.accept().get();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				socket.read(buffer).get();
				buffer.flip();
				String name = new String(buffer.array());
				System.out.println("Server Received:" + name);
				socket.close();
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		exec.execute(() -> {
			try {
				ready.await();
				AsynchronousSocketChannel socket = AsynchronousSocketChannel.open();
				socket.connect(new InetSocketAddress(10000)).get();
				String name = "lenicliu";
				socket.write(ByteBuffer.wrap(name.getBytes()));
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		exec.shutdown();
	}
}