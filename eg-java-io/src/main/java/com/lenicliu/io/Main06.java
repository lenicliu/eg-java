package com.lenicliu.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main06 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileWriter writer = new FileWriter("user.dat");
		writer.write("lenicliu, male");
		writer.close();

		FileReader reader = new FileReader("user.dat");
		int read = -1;
		while ((read = reader.read()) != -1) {
			System.out.print((char) read);
		}
		System.out.println();
		reader.close();
	}
}