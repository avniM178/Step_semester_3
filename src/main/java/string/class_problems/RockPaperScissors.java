package string.class_problems;

import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] computer = {"Scissors", "Paper", "Rock", "Scissors", "Rock"};
        String[][] game = new String[5][3];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < 5; i++) {

            System.out.println("\nRound " + (i + 1));

            System.out.print("Enter Rock, Paper or Scissors: ");
            game[i][0] = scanner.nextLine();

            game[i][1] = computer[i];

            game[i][2] = playRound(game[i][0], game[i][1]);

            if (game[i][2].equals("Player Wins")) {
                wins++;
            } else if (game[i][2].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.println("\nRound | Player | Computer | Result");

        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + " | "
                    + game[i][0] + " | "
                    + game[i][1] + " | "
                    + game[i][2]);
        }

        System.out.println("\nWins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.println("Win Percentage: " + (wins * 100.0 / 5) + "%");

        scanner.close();
    }

    public static String playRound(String player, String computer) {

        if (player.equalsIgnoreCase(computer)) {
            return "Draw";
        }

        if ((player.equalsIgnoreCase("Rock") && computer.equals("Scissors"))
                || (player.equalsIgnoreCase("Paper") && computer.equals("Rock"))
                || (player.equalsIgnoreCase("Scissors") && computer.equals("Paper"))) {

            return "Player Wins";
        }

        return "Computer Wins";
    }
}