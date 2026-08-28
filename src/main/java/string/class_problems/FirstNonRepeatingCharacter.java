package string.class_problems;

import java.util.Scanner;

public class FirstNonRepeatingCharacter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String text = scanner.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == ' ') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println("First Non-Repeating Character: " + result);
        }

        scanner.close();
    }

    public static char findFirstNonRepeatingChar(String text) {

        for (int i = 0; i < text.length(); i++) {

            char currentChar = text.charAt(i);
            int count = 0;

            for (int j = 0; j < text.length(); j++) {

                if (currentChar == text.charAt(j)) {
                    count++;
                }
            }

            if (count == 1) {
                return currentChar;
            }
        }

        return ' ';
    }
}