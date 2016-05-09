package com.lenicliu.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Main11 {
	public static void main(String[] args) {
		final InetSocketAddress localhost = new InetSocketAddress("127.0.0.1", 10000);

		new Thread(() -> {
			try {
				ServerSocketChannel server = ServerSocketChannel.open();
				server.bind(localhost);
				SocketChannel socket = server.accept();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				socket.read(buffer);
				buffer.flip();
				String name = new String(buffer.array());
				System.out.println("server recieved: " + name);
				socket.write(ByteBuffer.wrap(("Hi, " + name).getBytes()));
				socket.close();
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				SocketChannel socket = SocketChannel.open();
				socket.connect(localhost);
				socket.write(ByteBuffer.wrap("lenicliu".getBytes()));
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				socket.read(buffer);
				buffer.flip();
				System.out.println("client recieved: " + new String(buffer.array()));
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}