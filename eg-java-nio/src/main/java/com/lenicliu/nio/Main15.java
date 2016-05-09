package com.lenicliu.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main15 {
	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();
		try {
			File file = new File("gigabyte-copy.dat");
			file.deleteOnExit();
			Files.copy(Paths.get("gigabyte.dat"), new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}