package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File[] files = new File(packageName).listFiles();
        ClassLoaderFromPath classLoader = new ClassLoaderFromPath();

        for (File f : files) {
            Class<?> clazz = classLoader.load(f.toPath());
            if (clazz != null)
                hiddenClasses.add(clazz);
        }

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        int count = 0;
        Class finded = null;
        Constructor constructor;


        for (Class clazz: hiddenClasses) {


            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                count++;
                finded = clazz;
            }

        }


        if (count == 1) {
            try {
                constructor = finded.getDeclaredConstructor();
                constructor.setAccessible(true);
                return (HiddenClass) constructor.newInstance();
            } catch (Exception e) {

            }

        }

        return null;
    }

    public static class ClassLoaderFromPath extends ClassLoader {
        public Class<?> load(Path path) {
            try {
                if (path.getFileName().toString().lastIndexOf(".class") == -1)
                    return null;

                byte[] b = Files.readAllBytes(path);
                return defineClass(null, b, 0, b.length); //here main magic
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

