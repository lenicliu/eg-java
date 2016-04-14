package com.lenicliu.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

	public static String format(Thread t) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
		String template = "%s, id=%s, name=%s, priority=%s, state=%s, group=%s, alive=%s, interrupted=%s, daemon=%s";
		return String.format(template, format.format(new Date()), t.getId(), t.getName(), t.getPriority(), t.getState(),
				getGroupName(t), t.isAlive(), t.isInterrupted(), t.isDaemon());
	}

	private static String getGroupName(Thread t) {
		return t.getThreadGroup() == null ? "" : t.getThreadGroup().getName();
	}

	public static void process(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	public static void running(String prefix, Runnable runnable, int num) throws Exception {
		Thread[] threads = new Thread[num];
		for (int i = 0; i < num; i++) {
			threads[i] = new Thread(runnable, prefix + "-" + i);
		}
		for (int i = 0; i < num; i++) {
			threads[i].start();
		}
		for (int i = 0; i < num; i++) {
			threads[i].join();
		}
	}
}