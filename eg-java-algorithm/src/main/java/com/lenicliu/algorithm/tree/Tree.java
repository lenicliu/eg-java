package com.lenicliu.algorithm.tree;

public interface Tree<T> {
	void insert(T value);

	boolean contains(T value);

	void remove(T value);
}