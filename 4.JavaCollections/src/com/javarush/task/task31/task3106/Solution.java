package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            return;
        }

        String resultFileName = args[0];

        String[] fileParts = Arrays.copyOfRange(args,1,args.length);
        Arrays.sort(fileParts);

        List zipParts= new ArrayList<>();
        for (int i = 0; i < fileParts.length; i++) {
            zipParts.add(new FileInputStream(fileParts[i]));
        }

        byte[] buffer = new byte[1024];
        int readByteCnt;
        try(ZipInputStream zipIn = new ZipInputStream(new SequenceInputStream(Collections.enumeration(zipParts)));
            FileOutputStream fileOut = new FileOutputStream(new File(resultFileName))) {

            zipIn.getNextEntry();

            while ((readByteCnt = zipIn.read(buffer)) > 0) {
                fileOut.write(buffer, 0, readByteCnt);
                fileOut.flush();
            }
        }
    }
}
