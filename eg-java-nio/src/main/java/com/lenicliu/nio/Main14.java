package com.lenicliu.nio;

import java.io.IOException;
import java.nio.channels.Selector;

public class Main14 {
	public static void main(String[] args) throws IOException {
		try {
			Selector selector = Selector.open();
			selector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}