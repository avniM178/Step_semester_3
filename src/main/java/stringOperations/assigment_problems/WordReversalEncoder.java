package stringOperations.assigment_problems;

import java.util.Scanner;

public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {

        String[] words = sentence.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {

            StringBuilder reverse = new StringBuilder(words[i]);
            result = result + reverse.reverse();

            if (i != words.length - 1) {
                result = result + " ";
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = scanner.nextLine();

        System.out.println(reverseEachWord(sentence));

        scanner.close();
    }
}