package com.lenicliu.algorithm.tree.impl;

import java.util.TreeSet;

import com.lenicliu.algorithm.tree.Tree;

public class JDKTree<T> implements Tree<T> {

	private TreeSet<T> tree = new TreeSet<>();

	@Override
	public void insert(T value) {
		tree.add(value);
	}

	@Override
	public boolean contains(T value) {
		return tree.contains(value);
	}

	@Override
	public void remove(T value) {
		tree.remove(value);
	}
	
	@Override
	public String toString() {
		return tree.toString().replaceAll(" ", "");
	}
}