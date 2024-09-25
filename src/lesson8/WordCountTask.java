package lesson8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

public class WordCountTask implements Callable<Map<String, Integer>> {
    private final List<String> lines;

    public WordCountTask(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public Map<String, Integer> call() {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordCount;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        List<String> lines = Files.readAllLines(Paths.get("largefile.txt"));
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += 1000) {
            List<String> partition = lines.subList(i, Math.min(i + 1000, lines.size()));
            futures.add(executor.submit(new WordCountTask(partition)));
        }

        Map<String, Integer> totalCount = new HashMap<>();
        for (Future<Map<String, Integer>> future : futures) {
            Map<String, Integer> partialCount = future.get();
            partialCount.forEach((k, v) -> totalCount.put(k, totalCount.getOrDefault(k, 0) + v));
        }

        totalCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        executor.shutdown();
    }
}
