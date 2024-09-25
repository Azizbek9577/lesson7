package lesson7;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;
import java.util.*;
    public class TextProcessor {
        public static void main(String[] args) throws IOException {
            File file = new File("text.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String text = br.readLine();

            pattern(text, "\\d", "Bir xonali sonlar");

            pattern(text, "\\d{2}", "Ikki xonali sonlar");

            pattern(text, "\\b[3-8]{3}\\b", "Uch xonali (3-8) sonlar");

            pattern(text, "\\b[a-z]{3,7}\\b", "Kichkina harf bilan yozilgan so'zlar");

            pattern(text, "\\b[A-Z]{4,7}\\b", "Katta harf bilan yozilgan so'zlar");

            pattern(text, "\\bfree\\w*\\b", "\"free\" bilan boshlanadigan so'zlar");

            pattern(text, "\\b\\w*stu\\b", "\"stu\" bilan tugagan so'zlar");




        }

        public static void pattern(String text , String regex , String description){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            int count = 0;
            System.out.println(description+";");

            while(matcher.find()){
                System.out.println(matcher.group());
                count++;
            }

            System.out.println(count);
        }
    }
