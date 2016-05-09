package com.lenicliu.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ShootingGame {

	public static String	DOUBLELINE	= "============================================================================================";
	public static String	SIGLELINE	= "--------------------------------------------------------------------------------------------";

	public static void dashboard(int round) {
		System.out.println(ShootingGame.DOUBLELINE);
		StringBuilder rank = new StringBuilder();
		rank.append("name\tscore");
		for (int i = 0; i < round; i++) {
			rank.append("\t").append(i + 1);
		}
		System.out.println(rank.toString());
		System.out.println(ShootingGame.SIGLELINE);
	}

	public static void main(String[] args) {
		final int round = 10, players = 10;
		new Thread(new Match(round, players)).start();
	}
}

class Match implements Runnable {
	public Match(int round, int players) {
		super();
		this.round = round;
		this.players = players;
	}

	private int				round;
	private int				players;
	private ExecutorService	exec	= Executors.newCachedThreadPool();

	@Override
	public void run() {
		CountDownLatch ready = new CountDownLatch(1);
		CountDownLatch round = new CountDownLatch(this.round);
		List<Player> players = new ArrayList<>();

		CyclicBarrier done = new CyclicBarrier(this.round, new Referee(this.round, round, players));

		for (int i = 0; i < this.players; i++) {
			players.add(new Player(this.round, round, ready, done));
		}

		for (int i = 0; i < this.players; i++) {
			exec.execute(players.get(i));
		}
		ready.countDown();

		try {
			round.await();
		} catch (InterruptedException e) {
		}

		exec.shutdown();

		System.out.println(ShootingGame.DOUBLELINE);
		System.out.println("     " + players.get(0).getName());
		System.out.println(" " + players.get(1).getName() + " ----");
		System.out.println("----     " + players.get(2).getName());
		System.out.println("        " + "----");
	}
}

class Referee implements Runnable {
	private List<Player>	players;
	private CountDownLatch	round;
	private final int		r;

	public Referee(int r, CountDownLatch round, List<Player> players) {
		this.players = players;
		this.round = round;
		this.r = r;
	}

	@Override
	public void run() {
		Collections.sort(players);
		ShootingGame.dashboard(r);
		for (Player player : players) {
			System.out.println(player);
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		}
		round.countDown();
	}
}

class Player implements Runnable, Comparable<Player> {
	private static AtomicInteger	NUM		= new AtomicInteger(0);
	private Random					rand	= new Random(System.nanoTime());
	private String					name;
	private int						score	= 0;
	private CountDownLatch			ready;
	private CyclicBarrier			done;
	private CountDownLatch			round;
	private Integer[]				scores;

	public Player(int r, CountDownLatch round, CountDownLatch ready, CyclicBarrier done) {
		this.name = "P" + NUM.getAndIncrement();
		this.ready = ready;
		this.done = done;
		this.round = round;
		this.scores = new Integer[r];
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(name + "\t" + score);
		for (Integer score : scores) {
			s.append("\t").append(score == null ? "x" : score);
		}
		return s.toString();
	}

	@Override
	public int compareTo(Player o) {
		if (this.score == o.score) {
			return 0;
		} else {
			return this.score > o.score ? -1 : 1;
		}
	}

	@Override
	public void run() {
		while (round.getCount() != 0) {
			try {
				ready.await();
				int score = rand.nextInt(5) + 6;
				this.scores[this.scores.length - (int) round.getCount()] = score;
				this.score += score;
			} catch (InterruptedException e) {
			} finally {
				try {
					done.await();
				} catch (InterruptedException e) {
				} catch (BrokenBarrierException e) {
				}
			}
		}
	}
}