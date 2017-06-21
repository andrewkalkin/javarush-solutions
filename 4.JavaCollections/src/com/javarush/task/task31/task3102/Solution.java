package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        Queue<File> queue = new PriorityQueue<>();
        List<String> result = new ArrayList<>();

        File fileRoot = new File(root);



        if (fileRoot.isDirectory()) {

            File curFile;
            queue.offer(fileRoot);

            while ((curFile = queue.poll()) != null) {
                for (File file: curFile.listFiles())  {
                    if (file.isDirectory()) queue.offer(file); else
                        result.add(file.getAbsolutePath());
                }
            }

        }

        return result;

    }

    public static void main(String[] args) {
        try {
            for (String name: getFileTree("D:\\Music")) {
                System.out.println(name);
            }
        } catch (IOException e) {

        }

    }
}
