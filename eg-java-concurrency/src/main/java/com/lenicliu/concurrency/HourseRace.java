package com.lenicliu.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hourse Race Game
 * 
 * @author lenicliu
 *
 */
public class HourseRace {
	public static void main(String[] args) {
		new Race();
	}
}

class Lang {
	public static String repeat(int n, char c) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < n; i++) {
			s.append(c);
		}
		return s.toString();
	}

	public static void print(Object value) {
		System.out.println(value);
	}
}

class Race {
	private final int			DEST	= 50;
	private volatile boolean	done	= false;
	private List<Horse>			horses	= new ArrayList<>();
	private ExecutorService		exec	= Executors.newCachedThreadPool();
	private CyclicBarrier		barrier;

	public Race() {
		this(9);
	}

	public Race(int num) {
		barrier = new CyclicBarrier(num, () -> {
			Lang.print(Lang.repeat(DEST, '='));

			for (Horse horse : horses) {
				Lang.print(horse.tracks());
			}

			for (Horse horse : horses) {
				if (horse.getStrides() >= DEST) {
					Lang.print(horse + " won!");
					synchronized (this) {
						this.done = true;
					}
					exec.shutdown();
				}
			}

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		for (int i = 0; i < num; i++) {
			Horse horse = new Horse(this);
			horses.add(horse);
			exec.execute(horse);
		}
	}

	public synchronized boolean isDone() {
		return this.done;
	}

	public CyclicBarrier getBarrier() {
		return this.barrier;
	}
}

class Horse implements Runnable {
	private static int		counter	= 0;
	private static Random	rand	= new Random(47);
	private final int		id		= counter++;
	private int				strides	= 0;
	private CyclicBarrier	barrier;
	private Race			race;

	public Horse(Race race) {
		this.race = race;
		this.barrier = race.getBarrier();
	}

	public synchronized int getStrides() {
		return strides;
	}

	@Override
	public void run() {
		while (!race.isDone()) {
			synchronized (this) {
				this.strides += rand.nextInt(5);
			}
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "Horse " + String.format("%03d", this.id);
	}

	public String tracks() {
		StringBuilder race = new StringBuilder();
		race.append(Lang.repeat(getStrides(), '*'));
		race.append("");
		race.append(String.format("%03d", this.id));
		return race.toString();
	}
}