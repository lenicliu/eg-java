package com.lenicliu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.lenicliu.algorithm.tree.Tree;
import com.lenicliu.algorithm.tree.impl.AVLTree;
import com.lenicliu.algorithm.tree.impl.BTree;
import com.lenicliu.algorithm.tree.impl.JDKTree;

public class TreeTestCase {

	@Test
	public void print() {
		print(new BTree<>());
		print(new AVLTree<>());
		print(new JDKTree<>());
	}

	private void print(Tree<Integer> tree) {
		Random random = new Random(37);
		int[] ints = new int[20];
		for (int i = 0; i < 100; i++) {
			int value = random.nextInt(10000);
			tree.insert(value);
			if (i < 20) {
				ints[i] = value;
			}
		}
		for (int i = 0; i < 20; i++) {
			tree.remove(ints[i]);
		}
		System.out.println(tree.getClass().getSimpleName() + "\t" + tree);
	}

	@Test
	public void performance() {
		Random random = new Random(37);
		final int length = 10000;

		NamedArrayList<Integer> rand = new NamedArrayList<>("rand");
		NamedArrayList<Integer> asc = new NamedArrayList<>("asc");
		NamedArrayList<Integer> desc = new NamedArrayList<>("desc");
		for (int i = 0; i < length; i++) {
			rand.add(random.nextInt(Integer.MAX_VALUE));
			asc.add(i);
			desc.add(length - i);
		}

		List<NamedArrayList<Integer>> list = Arrays.asList(rand, asc, desc);
		for (NamedArrayList<Integer> insert : list) {
			for (NamedArrayList<Integer> search : list) {
				System.gc();
				System.out.println(" ----- \t insert(" + insert.getName() + ") \t search(" + search.getName() + ")");
				Tree<Integer> tree = null;
				long insertNano = 0, searchNano = 0;

				tree = new BTree<Integer>();
				insertNano = insert(tree, insert);
				searchNano = search(tree, search);
				System.out.println(tree.getClass().getSimpleName() + "\t " + insertNano + " \t " + searchNano);

				tree = new AVLTree<Integer>();
				insertNano = insert(tree, insert);
				searchNano = search(tree, search);
				System.out.println(tree.getClass().getSimpleName() + "\t " + insertNano + " \t " + searchNano);

				tree = new JDKTree<Integer>();
				insertNano = insert(tree, insert);
				searchNano = search(tree, search);
				System.out.println(tree.getClass().getSimpleName() + "\t " + insertNano + " \t " + searchNano);

				System.out.println();
				System.out.println();
			}
		}
	}

	private long insert(Tree<Integer> tree, List<Integer> source) {
		long begin = System.nanoTime();
		for (Integer value : source) {
			tree.insert(value);
		}
		long end = System.nanoTime();
		return (end - begin);
	}

	private long search(Tree<Integer> tree, List<Integer> source) {
		long begin = System.nanoTime();
		for (Integer value : source) {
			tree.contains(value);
		}
		long end = System.nanoTime();
		return (end - begin);
	}
}

class NamedArrayList<T> extends ArrayList<T> {

	private static final long	serialVersionUID	= 5219568032459893632L;
	private String				name;

	public NamedArrayList(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}