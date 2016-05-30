package com.lenicliu.algorithm.tree.impl;

import com.lenicliu.algorithm.tree.Tree;

public class BTree<T extends Comparable<T>> implements Tree<T> {
	private Node root = null;

	@Override
	public void insert(T value) {
		if (this.root == null) {
			this.root = new Node();
			this.root.value = value;
		} else {
			Node node = root;
			while (true) {
				if (node.value.compareTo(value) > 0) {
					if (node.left == null) {
						node.left = new Node();
						node.left.value = value;
						break;
					} else {
						node = node.left;
					}
				} else if (node.value.compareTo(value) < 0) {
					if (node.right == null) {
						node.right = new Node();
						node.right.value = value;
						break;
					} else {
						node = node.right;
					}
				} else {
					break;
				}
			}
		}
	}

	@Override
	public boolean contains(T value) {
		if (root == null) {
			return false;
		}
		Node node = root;
		while (node != null) {
			if (node.value.compareTo(value) == 0) {
				return true;
			} else if (node.value.compareTo(value) > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		toString(this.root, buffer);
		return "[" + (buffer.length() > 0 ? buffer.substring(1) : buffer.toString()) + "]";
	}

	private void toString(Node node, StringBuffer buffer) {
		if (node != null) {
			toString(node.left, buffer);
			buffer.append(",").append(node);
			toString(node.right, buffer);
		}
	}

	private class Node {
		T		value	= null;
		Node	left	= null;
		Node	right	= null;

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	@Override
	public void remove(T value) {
		this.root = remove(this.root, value);
	}

	private Node remove(Node node, T value) {
		if (node == null) {
			return null;
		}
		if (node.value.compareTo(value) > 0) {
			node.left = remove(node.left, value);
		} else if (node.value.compareTo(value) < 0) {
			node.right = remove(node.right, value);
		} else {
			if (node.left == null) {
				return node.right;
			}
			if (node.right == null) {
				return node.left;
			}
			Node n = node.left;
			while (n.right != null) {
				n = n.right;
			}
			node.value = n.value;
			node.left = remove(node.left, n.value);
			return node;
		}
		return node;
	}
}