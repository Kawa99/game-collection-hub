import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors extends Game {

    private int currentScore = 0;

    public RockPaperScissors(Scanner scanner) {
        super("Rock, Paper, Scissors", "Choose rock, paper, or scissors to beat the computer!", scanner);
    }

    public void playGame() {
        currentScore = 0;
        Random rand = new Random();

        int playerChoice = 0;
        int computerChoice;

        System.out.println("*******************************");
        System.out.println("Welcome to Rock Paper Scissors!");
        System.out.println("*******************************");

        boolean play = true;
        while (play) {
            computerChoice = rand.nextInt(1, 4);
            System.out.print("Enter your choice (1. Rock, 2. Paper, 3. Scissors): ");
            boolean validChoice = false;
            while (!validChoice) {
                playerChoice = scanner.nextInt();
                if (playerChoice < 1 || playerChoice > 3) {
                    System.out.println("Invalid choice");
                    System.out.print("Enter your choice (1. Rock, 2. Paper, 3. Scissors): ");
                } else {
                    validChoice = true;
                }
            }
            if (playerChoice == computerChoice) {
                System.out.println("\nIt's a tie!");
                System.out.println("Current Score: " + currentScore);
            } else if ((playerChoice == 1 && computerChoice == 3)
                    || (playerChoice == 2 && computerChoice == 1)
                    || (playerChoice == 3 && computerChoice == 2)) {
                System.out.println("\nYou win!");
                currentScore++;
                System.out.println("Current Score: " + currentScore);
            } else {
                System.out.println("\nYou lose!");
                System.out.println("Current Score: " + currentScore);
            }

            boolean validChar = false;
            while (!validChar) {
                System.out.print("Play again? (Y/N): ");
                char choice = scanner.next().charAt(0);
                switch (choice) {
                    case 'Y', 'y' -> validChar = true;
                    case 'N', 'n' -> {
                        validChar = true;
                        play = false;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }

    public int getCurrentScore() {
        return currentScore;
    }
}
