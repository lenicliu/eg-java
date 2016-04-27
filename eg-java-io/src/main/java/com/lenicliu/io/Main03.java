package com.lenicliu.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main03 {
	public static void main(String[] args) throws IOException {
		InputStream input = new ByteArrayInputStream(new byte[] { (byte) 4 });
		int read = -1;
		while ((read = input.read()) != -1) {
			System.out.println(read);
		}
		input.close();

		OutputStream output = new ByteArrayOutputStream();
		output.write(new byte[] { (byte) 2, (byte) 39 });
		output.close();
	}
}