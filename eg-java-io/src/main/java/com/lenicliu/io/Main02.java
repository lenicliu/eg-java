package com.lenicliu.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Main02 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("user.dat", "rw");
		file.writeUTF("username:lenicliu\n");
		file.writeUTF("birthday:1987-09\n");
		file.close();
		
//		file = new RandomAccessFile("user.dat", "r");
		System.out.println(file.readLine());
		System.out.println(file.readLine());
		file.close();
	}
}