package stringOperations.class_problems;

import java.util.Scanner;

public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {

            char character = text.charAt(i);

            if (character == 'a' || character == 'e'
                    || character == 'i' || character == 'o'
                    || character == 'u'
                    || character == 'A' || character == 'E'
                    || character == 'I' || character == 'O'
                    || character == 'U') {

                vowels++;

            } else if (character != ' ') {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        countVowelsAndConsonants(text);

        scanner.close();
    }
}