package com.lenicliu.jvm;

/**
 * -Xmn10M -Xms20M -Xmx20M -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
 * -verbose:gc -Xloggc:gc.log
 * 
 * @author lenicliu
 *
 */
public class GCLog {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		final int M = 1024 * 1024;
		byte[] a1, a2, a3, a4, a5, a6, a7, a8;
		a1 = new byte[6 * M]; // Young[a1]
		a2 = new byte[1 * M]; // Young[a2]

		// Young -> Old (a1,a2)
		a3 = new byte[1 * M]; // Young[a3], Old[a1,a2]
		a4 = new byte[6 * M]; // Young[a3,a4], Old[a1,a2]

		// Young -> Old (a3)
		a5 = new byte[1 * M]; // Young[a4,a5], Old[a1,a2,a3]

		// Young -> Old (a5)
		a6 = new byte[1 * M]; // Young[a4,a6], Old[a1,a2,a3,a5]

		// Old (a2)
		a2 = null;
		a7 = new byte[1 * M]; // Young[a4,a6], Old[a1,a7,a3,a5]

		// Young (a4)
		a4 = null;
		a2 = new byte[4 * M]; // Young[a2,a6], Old[a1,a7,a3,a5]

		// Young & Old (a2,a1)
		// Young -> Old (a6)
		a1 = null;
		a2 = null;
		a4 = new byte[7 * M]; // Young[a4], Old[a7,a3,a5,a6]

		// GC Nothing
		a8 = new byte[1 * M]; // Young[a4], Old[a7,a3,a5,a6,a8]

		// Young -> 7M, Old -> 5M
	}
}