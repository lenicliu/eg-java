package com.lenicliu.thread;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main19 {

	public static void main(String[] args) throws Exception {
		final MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
		class Pi extends RecursiveTask<BigDecimal> {
			private static final long serialVersionUID = 3036795521436558838L;
			final long start, end, threshold = 10000;

			public Pi(long end) {
				this(1, end);
			}

			private Pi(long start, long end) {
				this.start = start;
				this.end = end;
			}

			@Override
			protected BigDecimal compute() {
				BigDecimal pi = BigDecimal.ZERO;
				if (end - start < threshold) {
					for (long i = start; i <= end; i++) {
						if (i % 2 == 1) {
							pi = pi.add(BigDecimal.ONE.divide(new BigDecimal(2 * i - 1), mc));
						} else {
							pi = pi.subtract(BigDecimal.ONE.divide(new BigDecimal(2 * i - 1), mc));
						}
					}
					pi = pi.multiply(new BigDecimal(4), mc);
				} else {
					long middle = (start + end) / 2;
					Pi left = new Pi(start, middle);
					Pi right = new Pi(middle + 1, end);
					left.fork();
					right.fork();
					pi = left.join().add(right.join());
				}
				return pi;
			}
		}

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		long begin = System.currentTimeMillis();
		System.out.println(forkJoinPool.submit(new Pi(10000000)).get());
		long end = System.currentTimeMillis();
		System.out.println("Time:" + (end - begin));
	}
}