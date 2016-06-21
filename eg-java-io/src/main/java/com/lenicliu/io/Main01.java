package com.lenicliu.io;

import java.io.File;
import java.io.IOException;

public class Main01 {
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("os.name"));
		@SuppressWarnings("unused")
		File file1 = new File("D:\\Document\\books\\java.pdf");
		@SuppressWarnings("unused")
		File file2 = new File("/opt/data/my.txt");
		@SuppressWarnings("unused")
		File file3 = new File("/opt/data", "my.txt");

		File file = new File("./pom.xml");
		System.out.println("Absolute path = " + file.getAbsolutePath());
		System.out.println("Canonical path = " + file.getCanonicalPath());
		System.out.println("Name = " + file.getName());
		System.out.println("Parent = " + file.getParent());
		System.out.println("Path = " + file.getPath());
		System.out.println("Is absolute = " + file.isAbsolute());
		System.out.println();
		File dir = new File(".");
		File[] folders = dir.listFiles((f) -> f.isDirectory());
		for (File folder : folders) {
			System.out.println(folder);
		}
	}
}