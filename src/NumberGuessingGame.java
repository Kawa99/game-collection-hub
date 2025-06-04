import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame extends Game {

    private final static int LOWER_BOUND = 1;
    private final static int UPPER_BOUND = 10;
    private int currentScore = 0;

    public NumberGuessingGame(Scanner scanner) {
        super("Number Guessing Game", "Guess the number between 1 and 10!", scanner);
    }

    public void playGame() {
        currentScore = 0;
        Random rand = new Random();
        boolean numberGuessed = false;
        int attempts = 0;

        int number = rand.nextInt(LOWER_BOUND, UPPER_BOUND + 1);

        System.out.println("\n================================");
        System.out.println("Welcome to Number Guessing Game");
        System.out.println("================================");
        System.out.println("I'm thinking of a number between " + LOWER_BOUND + " and " + UPPER_BOUND);

        while (!numberGuessed) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess < LOWER_BOUND || guess > UPPER_BOUND) {
                System.out.println("Invalid number! Please enter a number between " + LOWER_BOUND + " and " + UPPER_BOUND);
                attempts--; // Don't count invalid guesses
            } else if (guess == number) {
                System.out.println("Congratulations! You guessed the number " + number + " in " + attempts + " attempts!");
                numberGuessed = true;
            } else if (guess > number) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Too low! Try again.");
            }
        }

        // Calculate score (fewer attempts = higher score)
        currentScore += Math.max(1, 11 - attempts);
        System.out.println("Your score: " + currentScore + " points!");
    }

    public int getCurrentScore() {
        return currentScore;
    }
}