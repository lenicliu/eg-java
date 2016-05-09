package com.lenicliu.nio;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;

public class Main16 {
	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Iterable<FileStore> stores = fs.getFileStores();
		dump(stores);

		System.out.println();
		Iterable<Path> paths = fs.getRootDirectories();
		dump(paths);

		System.out.println();
		System.out.println(fs.getSeparator());

		System.out.println();
		Set<String> views = fs.supportedFileAttributeViews();
		dump(views.iterator());

		System.out.println();
		Path path1 = fs.getPath("/User", "lenicliu");
		System.out.println(path1.getFileName());
		System.out.println(path1.toFile().getCanonicalPath());

		System.out.println();
		Path path2 = fs.getPath("/User/lenicliu");
		System.out.println(path2.getFileName());
		System.out.println(path2.toFile().getCanonicalPath());
		
	}

	public static <T> void dump(Iterator<T> iterator) {
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public static <T> void dump(Iterable<T> iterable) {
		Iterator<T> it = iterable.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}