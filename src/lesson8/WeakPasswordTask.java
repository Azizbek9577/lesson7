package lesson8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WeakPasswordTask implements Callable<String> {
    private final String password;

    public WeakPasswordTask(String password) {
        this.password = password;
    }

    @Override
    public String call() {
        if (!isStrong(password)) {
            return password;
        }
        return null;
    }

    private boolean isStrong(String password) {
        return password.length() >= 8 &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try (BufferedReader br = new BufferedReader(new FileReader("passwords.txt"))) {
            String line;
            List<Future<String>> futures = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                futures.add(executor.submit(new WeakPasswordTask(line)));
            }

            for (Future<String> future : futures) {
                String weakPassword = future.get();
                if (weakPassword != null) {
                    System.out.println("Weak password: " + weakPassword);
                }
            }
        }
        executor.shutdown();
    }
}
