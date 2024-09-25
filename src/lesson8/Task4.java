package lesson8;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task4 {
    public static void main(String[] args) throws IOException {
        String check1 = " ";
        String check2 = "$";
        String check3 = "*";
        String check4 = "&";
        String check5 = "#";
        List<String> lis = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(("Txt.text")));

        String line = "";
        while (br.ready()) {
            line += (char)br.read();
            if (line.length() == 1000) {
                lis.add(line);
                line = "";
            }
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        File file = new File("Txt.txt");
        file.createNewFile();
        if (lis.size() == 0 || lis.get(lis.size() - 1) != line) {
            lis.add(line);
        }
        for (int i = 0; i < lis.size(); i++) {
            int finalI = i;

            String j = lis.get(i);
            executorService.execute(() -> {

                String replace = j.replace(check2, "").replace(check3, "");
                String replace1 = replace.replace(check4, "").replace(check5, "").replaceAll("\\ {2,}", " ");
                try {
                    Files.writeString(file.toPath(), replace1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }
}
