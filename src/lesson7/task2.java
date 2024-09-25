package lesson7;
import java.io.*;
import java.util.*;
import java.util.regex.*;

    public class task2 {
        public static boolean isPrime(int number) {
            if (number <= 1) return false;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) return false;
            }
            return true;
        }

        public static void readPrimesFromFile(File file) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(line);
            List<Integer> primes = new ArrayList<>();
            while (m.find()) {
                int number = Integer.parseInt(m.group());
                if (isPrime(number)) primes.add(number);
            }
            br.close();
            System.out.println("Prime numbers: " + primes);
        }

        public static void main(String[] args) throws IOException {
            File folder = new File("C:/");
            File[] files = folder.listFiles();
            File fileToFind = null;

            for (File file : files) {
                if (file.getName().equals("numbers.txt")) {
                    fileToFind = file;
                    break;
                }
            }

            if (fileToFind != null) {
                System.out.println("File found: " + fileToFind.getAbsolutePath());
                readPrimesFromFile(fileToFind);
            } else {
                System.out.println("File not found");
            }
        }
    }

