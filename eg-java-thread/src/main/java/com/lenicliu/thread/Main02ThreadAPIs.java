package com.lenicliu.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Part 1 - Thread APIs <br/>
 * Chapter 2 : Synchronizing Access to Critical Sections <br/>
 * 
 * @author lenicliu
 *
 */
public class Main02ThreadAPIs {

	private static final int TRANSACTION = 10;

	public static void main(String[] args) throws Exception {
		// 1 reproduce problem
		Helper.running("A", new Pay(new Account1(500)), 2);

		// 2 synchronized method
		Helper.running("B", new Pay(new Account2(500)), 2);

		// 3 synchronized block
		Helper.running("C", new Pay(new Account3(500)), 2);
	}

	static class Pay implements Runnable {
		private Logger	logger	= LoggerFactory.getLogger(getClass());
		private Account	account;

		public Pay(Account account) {
			super();
			this.account = account;
		}

		@Override
		public void run() {
			logger.info("Money : " + account.decrease(300));
		}
	}

	static abstract class Account {
		protected Logger	logger	= LoggerFactory.getLogger(getClass());
		protected long		money;

		public Account(long money) {
			super();
			this.money = money;
		}

		public abstract long decrease(long money);
	}

	static class Account1 extends Account {

		public Account1(long money) {
			super(money);
		}

		@Override
		public long decrease(long amount) {
			if (money > amount) {
				logger.info("processing....");
				Helper.process(TRANSACTION);
				money -= amount;
			} else {
				logger.info("Money not enough");
			}
			return money;
		}
	}

	static class Account2 extends Account {

		public Account2(long money) {
			super(money);
		}

		@Override
		public synchronized long decrease(long amount) {
			if (money > amount) {
				logger.info("processing....");
				Helper.process(TRANSACTION);
				money -= amount;
			} else {
				logger.info("Money not enough");
			}
			return money;
		}
	}

	static class Account3 extends Account {

		private final Object LOCK = new Object();

		public Account3(long money) {
			super(money);
		}

		@Override
		public long decrease(long amount) {
			synchronized (LOCK) {
				if (money > amount) {
					logger.info("processing....");
					Helper.process(TRANSACTION);
					money -= amount;
				} else {
					logger.info("Money not enough");
				}
				return money;
			}
		}
	}
}