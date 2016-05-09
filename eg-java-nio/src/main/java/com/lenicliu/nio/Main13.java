package com.lenicliu.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

public class Main13 {
	public static void main(String[] args) throws IOException {
		final Pipe pipe = Pipe.open();
		new Thread(() -> {
			try {
				SinkChannel channel = pipe.sink();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				buffer.put("Hi, lenicliu".getBytes());
				buffer.flip();// flip before write
				System.out.println("write: " + new String(buffer.array()));
				channel.write(buffer);
				channel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				SourceChannel channel = pipe.source();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				channel.read(buffer);
				channel.close();
				buffer.flip();
				System.out.println("read: " + new String(buffer.array()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}