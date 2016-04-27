package com.lenicliu.algorithm;

import java.util.Arrays;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Sort Test Case
 * 
 * @author Lenicliu 2015-01-08
 *
 */
public class SortTestCase {

	private void verify(int[] array) {
		for (int i = 1; i < array.length; i++) {
			Assert.assertFalse(array[i - 1] > array[i]);
		}
	}

	private static final int	LENGTH	= 100000;

	private static final int[]	INTS	= new int[LENGTH];
	static {
		for (int i = 0; i < INTS.length; i++) {
			INTS[i] = RandomUtils.nextInt(0, Integer.MAX_VALUE);
		}
	}

	@Test
	public void testBubbleSort() {
		int[] array = Arrays.copyOf(INTS, LENGTH);
		Sort.bubbleSort(array);
		verify(array);
	}

	@Test
	public void testBubbleSortOptimized() {
		int[] array = Arrays.copyOf(INTS, LENGTH);
		Sort.bubbleSortOptimized(array);
		verify(array);
	}

	@Test
	public void testInsertSort() {
		int[] array = Arrays.copyOf(INTS, LENGTH);
		Sort.insertSort(array);
		verify(array);
	}

	@Test
	public void testSelectSort() {
		int[] array = Arrays.copyOf(INTS, LENGTH);
		Sort.selectSort(array);
		verify(array);
	}

	@Test
	public void testQuickSort() {
		int[] array = Arrays.copyOf(INTS, LENGTH);
		Sort.quickSort(array);
		verify(array);
	}

	@Test
	public void testQuickSortOptimized() {
		int[] array = Arrays.copyOf(INTS, LENGTH);
		Sort.quickSortOptimized(array);
		verify(array);
	}
}