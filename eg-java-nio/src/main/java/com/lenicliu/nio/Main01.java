package com.lenicliu.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Main01 {
	// 0 <= mark <= position <= limit <= capacity
	public static void main(String[] args) {

		print(ByteBuffer.allocate(6));
		System.out.println();

		print(ByteBuffer.allocateDirect(6));
		System.out.println();

		// mark = position = 0
		print(ByteBuffer.allocate(6).mark());
		System.out.println();

		// mark = 0, position = 2
		print(ByteBuffer.allocate(6).mark().position(2));
		System.out.println();

		// mark = 0, position = 0
		print(ByteBuffer.allocate(6).mark().position(2).reset());
		System.out.println();

		// read & write
		ByteBuffer buffer = ByteBuffer.allocate(10);
		for (byte value = 0; value < 10; value++) {
			buffer.put(value);
		}
		for (int index = 0; index < buffer.position(); index++) {
			System.out.println(buffer.get(index));
		}

		// overflow
		buffer.clear();
		for (byte value = 0; value < 11; value++) {
			try {
				buffer.put(value);
			} catch (Exception e) {
				System.out.println(e.toString() + ": capacity=" + buffer.capacity() + ", position=" + buffer.position());
			}
		}
	}

	public static void print(Buffer buffer) {
		System.out.println("capacity:\t" + buffer.capacity());
		System.out.println("limit:  \t" + buffer.limit());
		System.out.println("position:\t" + buffer.position());
	}
}