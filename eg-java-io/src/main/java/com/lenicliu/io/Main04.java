package com.lenicliu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main04 {
	public static void main(String[] args) {
		FileOutputStream output = null;
		try {
			output = new FileOutputStream("user.dat");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			output.write("lenicliu, male".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (FileInputStream input = new FileInputStream("user.dat")) {
			byte[] buffer = new byte[input.available()];
			input.read(buffer);
			// input.close();
			System.out.println(new String(buffer));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}