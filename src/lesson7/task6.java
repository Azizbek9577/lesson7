package lesson7;

import java.io.*;

public class task6 {

    public static void copyFiles(File sourceFolder, File destinationFolder) throws IOException {
        if (!destinationFolder.exists()) {
            destinationFolder.mkdir();
        }

        for (File file : sourceFolder.listFiles()) {
            if (file.isFile()) {
                File destFile = new File(destinationFolder, file.getName());
                copyFile(file, destFile);
            }
        }
    }

    public static void copyFile(File source, File destination) throws IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File sourceFolder = new File("C:/SourceFolder");
        File destinationFolder = new File("C:/DestinationFolder");
        copyFiles(sourceFolder, destinationFolder);
        System.out.println("Files copied successfully.");
    }
}
