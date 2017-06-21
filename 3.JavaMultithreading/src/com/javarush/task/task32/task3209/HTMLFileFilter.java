package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by kalinnikov_al on 14.03.2017.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return (f.isDirectory() || f.getName().toLowerCase().trim().endsWith(".htm") || f.getName().toLowerCase().trim().endsWith(".html"));
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
