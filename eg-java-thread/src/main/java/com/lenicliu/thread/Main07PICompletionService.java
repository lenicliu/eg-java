package com.lenicliu.thread;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main07PICompletionService {

	public static void main(String[] args) throws Exception {

		class CalculatePI implements Callable<BigDecimal> {
			int n;

			public CalculatePI(int n) {
				this.n = n;
			}

			@Override
			public BigDecimal call() throws Exception {
				MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
				// pi/4 = 1/1 - 1/3 + 1/7 - 1/9 + 1/11 - 1/13 .....
				BigDecimal pi = BigDecimal.ZERO;
				for (int i = 1; i <= n; i++) {
					if (i % 2 == 1) {
						pi = pi.add(BigDecimal.ONE.divide(new BigDecimal(2 * i - 1), mc));
					} else {
						pi = pi.subtract(BigDecimal.ONE.divide(new BigDecimal(2 * i - 1), mc));
					}
				}
				return pi.multiply(new BigDecimal(4), mc);
			}
		}

		ExecutorService es = Executors.newFixedThreadPool(8);
		CompletionService<BigDecimal> cs = new ExecutorCompletionService<BigDecimal>(es);
		long begin = System.currentTimeMillis();
		cs.submit(new CalculatePI(10000000));
		System.out.println(cs.take().get());
		long end = System.currentTimeMillis();
		System.out.println("Time:" + (end - begin));
		es.shutdown();
	}
}