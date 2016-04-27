package com.lenicliu.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main08 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		final int port = 10000;
		final String EOF = "\n";

		// server thread
		new Thread(() -> {
			try {
				ServerSocket server = new ServerSocket(port);
				Socket socket = server.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String name = reader.readLine();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				writer.write("Hi, " + name + EOF);
				writer.flush();
				socket.close();
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		// client thread
		new Thread(() -> {
			try {
				Socket socket = new Socket("localhost", port);
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				writer.write("lenicliu" + EOF);
				writer.flush();
				System.out.println(reader.readLine());
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}