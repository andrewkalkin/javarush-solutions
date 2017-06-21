package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strPath = reader.readLine();

        Path path = Paths.get(strPath);
        if (!Files.isDirectory(path)) {
            System.out.println(String.format("%s - не папка", path.toAbsolutePath()));
            return;
        }

        StandartFileVisitor myVisitor = new StandartFileVisitor();

        Files.walkFileTree(path, myVisitor);

        System.out.println(String.format("Всего папок - %d", myVisitor.getNumDirs()-1));
        System.out.println(String.format("Всего файлов - %d", myVisitor.getNumFiles()));
        System.out.println(String.format("Общий размер - %d", myVisitor.getTotalAmount()));


    }

    private static class StandartFileVisitor extends SimpleFileVisitor<Path> {
        private long numDirs = 0;
        private long numFiles= 0;
        private long totalAmount = 0;

        public long getNumDirs() {
            return numDirs;
        }

        public long getNumFiles() {
            return numFiles;
        }

        public long getTotalAmount() {
            return totalAmount;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            numFiles++;
            totalAmount += Files.size(file);

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            numDirs++;
            return FileVisitResult.CONTINUE;
        }
    }
}
