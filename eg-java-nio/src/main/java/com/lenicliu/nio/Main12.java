package com.lenicliu.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Main12 {
	public static void main(String[] args) {
		final InetSocketAddress localhost = new InetSocketAddress("127.0.0.1", 10000);
		new Thread(() -> {
			try {
				DatagramChannel server = DatagramChannel.open();
				server.bind(localhost);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				SocketAddress address = server.receive(buffer);
				buffer.flip();
				String name = new String(buffer.array());
				System.out.println("server recieved: " + name);
				server.send(ByteBuffer.wrap(("Hi, " + name).getBytes()), address);
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				DatagramChannel client = DatagramChannel.open();
				client.send(ByteBuffer.wrap("lenicliu".getBytes()), localhost);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				client.receive(buffer);
				buffer.flip();
				System.out.println("client recieved: " + new String(buffer.array()));
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}