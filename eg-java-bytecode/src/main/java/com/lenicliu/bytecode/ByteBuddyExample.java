package com.lenicliu.bytecode;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

import java.lang.reflect.Method;

/**
 * Created by lenicliu on 10/9/16.
 */
public class ByteBuddyExample {
    public static void main(String[] args) throws Exception {
        ByteBuddy buddy = new ByteBuddy();
        DynamicType.Builder<Object> builder = buddy.subclass(Object.class).name("Cal");
        builder.defineMethod("add", int.class)
                .withParameter(int.class)
                .withParameter(int.class)
                .intercept(null);
        Class<?> clazz = builder.make().load(Thread.currentThread().getContextClassLoader()).getLoaded();
        Object bean = clazz.newInstance();
        System.out.println(bean);
        for (Method m : bean.getClass().getDeclaredMethods()) {
            System.out.println(m);
        }
        Object invoke = bean.getClass().getMethod("add", int.class, int.class).invoke(bean, 1, 2);
        System.out.println(invoke);
    }
}
