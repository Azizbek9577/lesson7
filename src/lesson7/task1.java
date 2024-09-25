package lesson7;

import java.io.File;

public class task1 {

    public static long folderSize(File directory) {
        long length = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += folderSize(file);
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        File folder = new File("C:/Downloads");
        System.out.println("Total size of folder: " + folderSize(folder) / (1024 * 1024) + " MB");
    }
}

