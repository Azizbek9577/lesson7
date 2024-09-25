package lesson8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String emailRegex ="^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        BufferedReader bufferedReader = new BufferedReader(new FileReader("uyga1"));
        String emails = "";

        while (bufferedReader.ready()) {
            emails += bufferedReader.readLine();
            emails += " ";
        }
        AtomicInteger a= new AtomicInteger();
        String[] s = emails.split(" ");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            String s1 = s[i];
            executorService.execute(() -> {

                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(s1);
                if (!matcher.find()) {
                    System.out.println(s1);
                    a.incrementAndGet();
                }

            });


        }
        Thread.sleep(4000);
        System.out.println(a.get());
    }
}
