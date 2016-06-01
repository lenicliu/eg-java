package com.lenicliu.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

public class CacheRouter {

	private final int						NODES	= 100;
	private NavigableMap<String, String>	vnodes	= null;
	private List<String>					rnodes	= null;

	public CacheRouter() {
		this.vnodes = Collections.synchronizedNavigableMap(new TreeMap<>());
		this.rnodes = Collections.synchronizedList(new LinkedList<>());
	}

	private String hash(String value) {
		return DigestUtils.sha256Hex(value);
	}

	public void insert(String ip) {
		rnodes.add(ip);
	}

	public void remove(String ip) {
		rnodes.remove(ip);
	}

	public void mapping() {
		vnodes.clear();
		for (String rnode : rnodes) {
			for (int j = 0; j < NODES; j++) {
				vnodes.put(hash(rnode + "#" + j), rnode);
			}
		}
		System.out.println(toString(false));
	}

	public String get(String key) {
		SortedMap<String, String> nodes = vnodes.tailMap(hash(key));
		return nodes.size() == 0 ? vnodes.get(vnodes.firstKey()) : nodes.get(nodes.firstKey());
	}

	public String toString(boolean v) {
		StringBuilder value = new StringBuilder();
		value.append(rnodes.toString());
		if (v) {
			value.append("\n");
			value.append(vnodes.toString());
		}
		return value.toString();
	}

	@Override
	public String toString() {
		return toString(true);
	}
}