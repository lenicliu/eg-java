package com.lenicliu.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;

public class Main10 {
	public static void main(String[] args) throws IOException {
		System.gc();
		copy2("gigabyte.dat", "gigabyte-copy.dat");
		System.gc();
		copy1("gigabyte.dat", "gigabyte-copy.dat");
		System.gc();
		copy0("gigabyte.dat", "gigabyte-copy.dat");
	}

	public static void clean(String src, String dest) {
		new File(dest).delete();
	}

	public static void copy0(String src, String dest) {
		clean(src, dest);
		long begin = System.currentTimeMillis();
		try (FileInputStream in = new FileInputStream(src)) {
			in.getChannel().transferTo(0, in.getChannel().size(), Channels.newChannel(new FileOutputStream(dest)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

	public static void copy1(String src, String dest) {
		clean(src, dest);
		long begin = System.currentTimeMillis();
		
//		File src = new File("/path/of/srcFile");
//		File dest = new File("/path/of/destFile");
		
		FileInputStream in = null;
		FileOutputStream ot = null;
		
		try {
			in = new FileInputStream(src);
			ot = new FileOutputStream(dest);

			FileChannel ic = in.getChannel();
			FileChannel oc = ot.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(1024 * 8);
			buffer.clear();

			while (ic.read(buffer) != -1) {
				buffer.flip(); // flip before write
				oc.write(buffer);
				buffer.clear(); // make buffer empty for reading
			}
		} catch (IOException e) {
			// handle exception
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ignore) {
				}
			}
			if (ot != null) {
				try {
					ot.close();
				} catch (IOException ignore) {
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

	static final int EOF = -1;

	public static void copy2(String src, String dest) {
		clean(src, dest);
		long begin = System.currentTimeMillis();
		try (FileInputStream in = new FileInputStream(src); FileOutputStream ot = new FileOutputStream(dest)) {
			byte[] buffer = new byte[1024 * 8];
			int read = EOF;
			while (EOF != (read = in.read(buffer))) {
				ot.write(buffer, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}