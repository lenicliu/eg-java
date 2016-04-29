package com.lenicliu.thread;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main17 {

	public static void main(String[] args) throws Exception {

		class Factorial extends RecursiveTask<BigDecimal> {
			private static final long serialVersionUID = 5851034591867289288L;
			final long start, end, threshold = 10;

			public Factorial(long end) {
				this(1, end);
			}

			private Factorial(long start, long end) {
				this.start = start;
				this.end = end;
			}

			@Override
			protected BigDecimal compute() {
				MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
				BigDecimal factorial = BigDecimal.ONE;
				if (end - start < threshold) {
					for (long i = start; i <= end; i++) {
						factorial = factorial.multiply(new BigDecimal(i), mc);
					}
				} else {
					long middle = (start + end) / 2;
					Factorial left = new Factorial(start, middle);
					Factorial right = new Factorial(middle + 1, end);
					left.fork();
					right.fork();
					factorial = left.join().multiply(right.join());
				}
				return factorial;
			}
		}

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		System.out.println(forkJoinPool.submit(new Factorial(100)).get());
	}

	public static long factorialLong(long n) {
		if (n > 12) {
			throw new IllegalArgumentException("must less than 13");
		}
		int r = 1;
		for (int i = 1; i <= n; i++) {
			r *= i;
		}
		return r;
	}

	public static BigDecimal factorialBigDecimal(long n) {
		BigDecimal r = BigDecimal.ONE;
		for (int i = 1; i <= n; i++) {
			r = r.multiply(new BigDecimal(i));
		}
		return r;
	}
}