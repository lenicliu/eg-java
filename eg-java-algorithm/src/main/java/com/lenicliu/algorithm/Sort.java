package com.lenicliu.algorithm;

/**
 * Sort
 * 
 * @author Lenicliu 2015-01-08
 *
 */
public class Sort {

	/**
	 * Swap array[i] < - > array[j]
	 * 
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void swap(int[] array, int i, int j) {
		if (i != j) {
			int t = array[i];
			array[i] = array[j];
			array[j] = t;
		}
	}

	/**
	 * Bubble Sort
	 * 
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i] < array[j]) {
					swap(array, i, j);
				}
			}
		}
	}

	/**
	 * Bubble Sort Optimized
	 * 
	 * @param array
	 */
	public static void bubbleSortOptimized(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				if (array[i] > array[j]) {
					swap(array, i, j);
				}
			}
		}
	}

	/**
	 * Insert Sort
	 * 
	 * @param array
	 */
	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
				}
			}
		}
	}

	/**
	 * Select Sort
	 * 
	 * @param array
	 */
	public static void selectSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = array[i];
			int index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < min) {
					index = j;
					min = array[j];
				}
			}
			swap(array, i, index);
		}
	}

	/**
	 * Quick Sort
	 * 
	 * @param array
	 */
	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	public static void quickSort(int[] array, int i, int j) {
		if (i < j) {
			int index = i + 1, m = array[i];
			for (int k = index; k < j + 1; k++) {
				if (array[k] < m) {
					swap(array, k, index++);
				}
			}
			swap(array, i, index - 1);
			quickSort(array, i, index - 1);
			quickSort(array, index, j);
		}
	}

	/**
	 * Quick Sort Optimized
	 * 
	 * @param array
	 */
	public static void quickSortOptimized(int[] array) {
		quickSortOptimized(array, 0, array.length - 1);
	}

	public static void quickSortOptimized(int[] array, int i, int j) {
		if (i < j) {
			int m = array[(i + j) / 2], l = i - 1, h = j + 1;
			while (true) {
				while (array[++l] < m) {
				}
				while (array[--h] > m) {
				}
				if (l >= h) {
					break;
				}
				swap(array, l, h);
			}
			quickSort(array, i, l - 1);
			quickSort(array, h + 1, j);
		}
	}
}