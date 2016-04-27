package com.lenicliu.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main18 {

	public static void main(String[] args) throws Exception {
		Executor executor = (runnable) -> new Thread(runnable).start();
		executor.execute(() -> System.out.println("Hello Executor"));

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(() -> System.out.println("Hello ExecutorService"));
		executorService.shutdown();
	}
}