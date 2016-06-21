package com.lenicliu.algorithm;

import java.util.stream.IntStream;

import org.junit.Test;

public class CacheRouterTestCase {

	private static int[] KEYS = IntStream.range(0, 10).toArray();
	
	private void routing(CacheRouter router, int[] keys) {
		for (int key : keys) {
			String k = String.format("Key-%02d", key);
			String machine = router.get(k);
			System.out.println(k + "\t->\t" + machine);
		}
		System.out.println();
	}

	@Test
	public void staticRouting() {
		CacheRouter router = new CacheRouter();
		router.insert("192.168.1.100");
		router.insert("192.168.1.101");
		router.insert("192.168.1.102");
		router.mapping();

		routing(router, KEYS);
	}

	@Test
	public void dynamicRouting() {
		CacheRouter router = new CacheRouter();
		router.insert("192.168.1.100");
		router.insert("192.168.1.101");
		router.insert("192.168.1.102");
		router.mapping();
		routing(router, KEYS);

		router.insert("192.168.1.104");
		router.mapping();
		routing(router, KEYS);

		router.insert("192.168.1.105");
		router.mapping();
		routing(router, KEYS);

		router.remove("192.168.1.100");
		router.mapping();
		routing(router, KEYS);

		router.insert("192.168.1.100");
		router.mapping();
		routing(router, KEYS);
	}
}