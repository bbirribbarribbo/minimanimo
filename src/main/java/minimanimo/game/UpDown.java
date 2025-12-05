package minimanimo.game;

import minimanimo.User;
import java.util.Random;
import java.util.Scanner;

public class UpDown implements MiniGame {
    @Override
    public String getGameName() {
        return "UpDown";
    }

    @Override // Starts the game for a given user and returns the score.
    public int startGame(User user, Scanner scanner) {
        Random random = new Random();
        int targetNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 10;

        System.out.println("Welcome to UpDown! Guess a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts to guess the correct number.");

        while (attempts < maxAttempts) {
            System.out.println("Attempt: " + (attempts + 1) + ". Enter your guess: "); // Prompt user for input
            String input = scanner.next().trim();
            if (input.equals("0")) {
                System.out.println("Game cancelled by user.");
                return 0;
            }

            if (!input.matches("\\d+")) { // Validate input is a number
                System.out.println("Invalid input.");
                continue;
            }

            int guess = Integer.parseInt(input);
            if (guess < 1 || guess > 100) { // Validate input range
                System.out.println("Please guess a number between 1 and 100.");
                continue;
            }

            attempts++;
            if (guess == targetNumber) { // Correct guess
                System.out.println("Congratulations! You've guessed the correct number");
                int score = maxAttempts - attempts + 1;
                System.out.println("Your score is: " + score);
                return score;
            } else if (guess < targetNumber) { // Provide feedback
                System.out.println("Up!");
            } else {
                System.out.println("Down!");
            }

        }

        // Out of attempts
        System.out.println("Sorry, you've used all your attempts. The correct number was: " + targetNumber);
        return 0;
    }

    @Override // Indicates whether a lower score means a better performance.
    public boolean isLowerScoreBetter() {
        return false;
    }
}
