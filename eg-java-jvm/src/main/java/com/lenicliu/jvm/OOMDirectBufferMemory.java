package com.lenicliu.jvm;

import java.nio.ByteBuffer;

/**
 * -Xms16M -Xmx16M
 * 
 * @author lenicliu
 *
 */
public class OOMDirectBufferMemory {
	public static void main(String[] args) {
		ByteBuffer.allocateDirect(1024 * 1024 * 32);
	}
}