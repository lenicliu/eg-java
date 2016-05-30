package com.lenicliu.basic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SimpleProxy {
	interface Say {
		void say();
	}

	static class Hi implements Say {
		private String name;

		public Hi(String name) {
			this.name = name;
		}

		public void say() {
			System.out.println("Hi, " + name);
		}
	}

	static class HiProxy implements InvocationHandler {
		private Say say;

		public static Say newInstance(Say say) {
			ClassLoader cl = say.getClass().getClassLoader();
			Class<?>[] interfaces = new Class<?>[] { Say.class };
			return (Say) Proxy.newProxyInstance(cl, interfaces, new HiProxy(say));
		}

		private HiProxy(Say say) {
			this.say = say;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("before invoked...");
			try {
				return method.invoke(say, args);
			} finally {
				System.out.println("after invoke...");
			}
		}
	}

	public static void main(String[] args) {
		HiProxy.newInstance(new Hi("lenicliu")).say();
	}
}
