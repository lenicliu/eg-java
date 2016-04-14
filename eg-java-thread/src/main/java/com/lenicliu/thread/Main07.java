package com.lenicliu.thread;

public class Main07 {

	public static void main(String[] args) throws Exception {
		System.out.println(PI(6400000000L));
		System.out.println(Math.PI);
	}

	private static double PI(long n) {
		double pi = 0.0;
		for (long i = 1; i < n; i++) {
			pi += (i % 2 == 1) ? (1.0 / (2 * i - 1)) : -1.0 / (2 * i - 1);
		}
		return 4 * pi;
	}
}