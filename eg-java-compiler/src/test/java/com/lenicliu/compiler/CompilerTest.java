package com.lenicliu.compiler;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by lenicliu on 16/11/2.
 */
public class CompilerTest {
    @Test
    public void simple() throws Exception {
        String content = "package com.lenicliu;\n"
                + "public class HelloWorld {\n"
                + " public int add(int i, int j) {\n"
                + "  return i + j;\n"
                + " }\n"
                + "}";
        Compiler compiler = new Compiler();
        Class<?> compiledClass = compiler.compile("com.lenicliu.HelloWorld", content);
        Object instance = compiledClass.newInstance();
        Method method = instance.getClass().getMethod("add", int.class, int.class);
        Object result = method.invoke(instance, 1, 3);
        Assert.assertEquals(4, result);
    }
}
