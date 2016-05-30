package com.lenicliu.algorithm.tree.impl;

import java.util.Objects;

import com.lenicliu.algorithm.tree.Tree;

public class AVLTree<T extends Comparable<T>> implements Tree<T> {

	private Node root = null;

	@Override
	public void insert(T value) {
		this.root = insert(this.root, value);
	}

	private Node insert(Node node, T value) {
		if (node == null) {
			node = new Node(value);
		} else if (node.value.compareTo(value) > 0) {
			node.left = insert(node.left, value);
		} else if (node.value.compareTo(value) < 0) {
			node.right = insert(node.right, value);
		} else {
			return node;
		}

		setHeight(node);
		node = rotate(node);
		return node;
	}

	private Node rotate(Node node) {
		if (getHeight(node.left) - getHeight(node.right) == 2) {
			if (getHeight(node.left.left) > getHeight(node.left.right)) {
				node = lRotate(node);
			} else if (getHeight(node.left.left) < getHeight(node.left.right)) {
				node.left = rRotate(node.left);
				node = lRotate(node);
			}
		} else if (getHeight(node.right) - getHeight(node.left) == 2) {
			if (getHeight(node.right.right) > getHeight(node.right.left)) {
				node = rRotate(node);
			} else if (getHeight(node.right.right) < getHeight(node.right.left)) {
				node.right = lRotate(node.right);
				node = rRotate(node);
			}
		}
		return node;
	}

	private int getHeight(Node node) {
		return node == null ? 0 : node.height;
	}

	private void setHeight(Node node) {
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}

	private Node rRotate(Node node) {
		Node n = node.right;
		node.right = n.left;
		n.left = node;
		setHeight(node);
		setHeight(n);
		return n;
	}

	private Node lRotate(Node node) {
		Node n = node.left;
		node.left = n.right;
		n.right = node;
		setHeight(node);
		setHeight(n);
		return n;
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
		int		height	= 0;

		Node(T value) {
			this.value = Objects.requireNonNull(value);
		}

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
			setHeight(node);
			return node;
		}
		node = rotate(node);
		return node;
	}
}