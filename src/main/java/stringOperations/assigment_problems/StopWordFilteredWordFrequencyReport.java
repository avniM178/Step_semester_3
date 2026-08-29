package stringOperations.assigment_problems;

import java.util.*;

public class StopWordFilteredWordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {

        feedback = feedback.toLowerCase();
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        String[] words = feedback.split("\\s+");

        HashMap<String, Integer> count = new HashMap<>();

        for (String word : words) {

            boolean stop = false;

            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    stop = true;
                    break;
                }
            }

            if (!stop) {
                count.put(word, count.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(count.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String feedback = scanner.nextLine();

        printFilteredWordFrequency(feedback);

        scanner.close();
    }
}