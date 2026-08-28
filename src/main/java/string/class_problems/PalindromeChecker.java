package string.class_problems;
import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String text = scanner.nextLine();
        System.out.println("Iterative: "
                + isPalindromeIterative(text));
        System.out.println("Recursive: "
                + isPalindromeRecursive(text));
        System.out.println("Array Reversal: "
                + isPalindromeArrayReversal(text));
        scanner.close();
    }
    public static boolean isPalindromeIterative(String text) {
        int start = 0;
        int end = text.length() - 1;
        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(
                text.substring(1, text.length() - 1)
        );
    }
    public static boolean isPalindromeArrayReversal(String text) {
        char[] letters = text.toCharArray();
        String reversed = "";
        for (int i = letters.length - 1; i >= 0; i--) {
            reversed = reversed + letters[i];
        }
        return text.equals(reversed);
    }
}