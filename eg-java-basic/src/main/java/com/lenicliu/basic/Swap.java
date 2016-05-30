package com.lenicliu.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Swap {
	public void swap(int a, int b) {
		int temp;
		temp = a;
		a = b;
		b = temp;
	}

	public static void swapStatic(int a, int b) {
		int temp;
		temp = a;
		a = b;
		b = temp;
	}

	public <T> void swap(T a, T b) {
		T temp;
		temp = a;
		a = b;
		b = temp;
	}

	public static <T> void swapStatic(T a, T b) {
		T temp;
		temp = a;
		a = b;
		b = temp;
	}

	public int[] swapped(int a, int b) {
		return new int[] { b, a };
	}

	public void swapped(int[] ints, int i, int j) {
		int temp;
		temp = ints[i];
		ints[i] = ints[j];
		ints[j] = temp;
	}

	public static void main(String[] args) {
		int a = 10000, b = 20000;
		Swap.swapStatic(a, b);
		System.out.println("a = " + a + ", b = " + b);

		int m = 10000, n = 20000;
		new Swap().swap(m, n);
		System.out.println("m = " + m + ", n = " + n);

		Integer ai = new Integer(10000), bi = new Integer(20000);
		Swap.swapStatic(ai, bi);
		System.out.println("ai = " + ai + ", bi = " + bi);

		Integer mi = new Integer(10000), ni = new Integer(20000);
		new Swap().swap(mi, ni);
		System.out.println("mi = " + mi + ", ni = " + ni);

		List<String> al = new ArrayList<>(Arrays.asList("10000"));
		List<String> bl = new ArrayList<>(Arrays.asList("20000"));
		Swap.swapStatic(ai, bi);
		System.out.println("ai = " + ai + ", bi = " + bi);

		al = new ArrayList<>(Arrays.asList("10000"));
		bl = new ArrayList<>(Arrays.asList("20000"));
		new Swap().swap(al, bl);
		System.out.println("al = " + al + ", bl = " + bl);

		int i = 10000, j = 20000;
		int[] swapped = new Swap().swapped(i, j);
		System.out.println("swapped = " + swapped[0] + ", " + swapped[1]);

		int[] ints = new int[] { 10000, 20000 };
		new Swap().swapped(ints, 0, 1);
		System.out.println("ints = " + ints[0] + ", " + ints[1]);
	}
}