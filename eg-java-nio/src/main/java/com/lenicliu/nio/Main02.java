package com.lenicliu.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main02 {
	public static void main(String[] args) throws IOException {
		FileInputStream input = new FileInputStream("gigabyte.dat");
		FileChannel channel = input.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (channel.read(buffer) != -1) {
			buffer.flip();
			System.out.println(buffer.get());
			buffer.clear();
		}
		channel.close();
		input.close();
	}
}