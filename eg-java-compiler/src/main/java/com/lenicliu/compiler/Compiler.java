package com.lenicliu.compiler;

import org.apache.commons.io.FileUtils;

import javax.tools.*;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenicliu on 16/11/2.
 */
public class Compiler {

    public Class<?> compile(String className, String content) throws IOException {
        File file = new File("target/classes/com/lenicliu/HelloWorld.java");
        FileUtils.write(file, content);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> files = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file));

        List<String> options = Arrays.asList("-target", "1.8", "-d", "target/classes");

        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, options, null, files);
        if (!task.call()) {
            diagnostics.getDiagnostics().forEach((o) -> System.out.println(o));
            FileUtils.deleteQuietly(file);
            return null;
        } else {
            try {
                return this.getClass().getClassLoader().loadClass(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                FileUtils.deleteQuietly(file);
            }
        }
    }
}
