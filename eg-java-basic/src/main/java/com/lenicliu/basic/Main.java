package com.lenicliu.basic;

import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		System.out.println(true ^ true);
		System.out.println(false ^ true);
		System.out.println(true ^ false);
		System.out.println(false ^ false);
		System.out.println();

		System.out.println(0 ^ 1);
		System.out.println(0 ^ 0);
		System.out.println(1 ^ 1);
		System.out.println(1 ^ 0);
		System.out.println();

		int[] ints = new int[5];
		System.out.println((ints.length - 1) & new Object().hashCode());

		System.out.println();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (i != j) {
					System.out.println(i + " & " + j + " = " + (i & j));
					System.out.println(j + " & " + i + " = " + (j & i));
					System.out.println(i + " & " + i + " = " + (i & i));
					System.out.println(j + " & " + j + " = " + (j & j));
				}
			}
		}

		class HashCollision {
			@Override
			public int hashCode() {
				return 1 << 31;
			}
		}

		Map<HashCollision, Object> map = new HashMap<>();
		for (int i = 0, n = 1024; i < n; i++) {
			map.put(new HashCollision(), new Object());
		}
		System.out.println(map);
	}
}
