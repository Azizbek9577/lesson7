package lesson7;

import java.io.*;
import java.util.*;

public class task3 {

    public static void readNumbersFromFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int lineNumber = 1;

        while ((line = br.readLine()) != null) {
            String[] numbers = line.split(",");
            List<Integer> numList = new ArrayList<>();
            for (String num : numbers) {
                numList.add(Integer.parseInt(num.trim()));
            }
            System.out.println("Line " + lineNumber + ": " + numList);
            lineNumber++;
        }

        br.close();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("numbers.txt");
        readNumbersFromFile(file);
    }
}
