package lesson8;
import java.io.File;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class FileSearchTask extends RecursiveTask<Void> {
    private final File directory;
    private final String regex;

    public FileSearchTask(File directory, String regex) {
        this.directory = directory;
        this.regex = regex;
    }

    @Override
    protected Void compute() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    new FileSearchTask(file, regex).fork();
                } else if (file.getName().matches(regex)) {
                    System.out.println("Found: " + file.getAbsolutePath());
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        String regex = "\\w*mp.3\\b";
        File startDir = new File("C:/");
        pool.invoke(new FileSearchTask(startDir, regex));
    }
}
