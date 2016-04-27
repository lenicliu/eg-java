package com.lenicliu.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main07 {

	private static void useFileReader(File dat, int size) throws FileNotFoundException, IOException {
		long begin = System.currentTimeMillis();
		FileReader reader = new FileReader(dat);
		char[] buffer = new char[size];
		while (reader.read(buffer) != -1) {
		}
		reader.close();
		long end = System.currentTimeMillis();
		System.out.println("FileReader : \tbuffer size = " + size + ",\t" + (end - begin) / 1000.0 + " seconds.");
	}

	private static void useFileInputStream(File dat, int size) throws FileNotFoundException, IOException {
		long begin = System.currentTimeMillis();
		FileInputStream input = new FileInputStream(dat);
		byte[] buffer = new byte[size];
		while (input.read(buffer) != -1) {
		}
		input.close();
		long end = System.currentTimeMillis();
		System.out.println("FileInputStream : \tbuffer size = " + size + ",\t" + (end - begin) / 1000.0 + " seconds.");
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File dat = new File("gigabyte.dat");
		useFileReader(dat, 512);
		useFileReader(dat, 1024);
		useFileReader(dat, 2048);
		useFileReader(dat, 4096);
		useFileReader(dat, 8192);

		useFileInputStream(dat, 512);
		useFileInputStream(dat, 1024);
		useFileInputStream(dat, 2048);
		useFileInputStream(dat, 4096);
		useFileInputStream(dat, 8192);
	}
}