import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameHub {
    private ArrayList<Game> games;
    private Scanner scanner;
    private static final int MAIN_MENU_OPTIONS = 2;
    private static final int MINIMUM_CHOICE_NUMBER = 1;

    public GameHub() {
        games = new ArrayList<>();
        scanner = new Scanner(System.in);
        addGame(new RockPaperScissors(scanner));
    }

    public static void main(String[] args) {
        GameHub hub = new GameHub();
        hub.run();
    }

    private void run() {
        boolean isRunning = true;
        while (isRunning) {
            displayMainMenu();
            int choice = getUserChoice(MINIMUM_CHOICE_NUMBER, MAIN_MENU_OPTIONS);

            switch (choice) {
                case 1:
                    if (games.isEmpty()) {
                        System.out.println("No games available yet.\n");
                    } else {
                        displayGameMenu();
                        int gameChoice = getUserChoice(1, games.size());
                        try {
                            startSelectedGame(gameChoice - 1);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: Invalid selection.");
                        } catch (Exception e) {
                            System.out.println("Error: Game crashed unexpectedly. Returning to menu." + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    isRunning = false;
                    System.out.println("Thanks for playing! Goodbye!");
                    break;
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("==== GAME HUB MAIN MENU ====");
        System.out.println("1. Play Games");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    private void displayGameMenu() {
        System.out.println("==== SELECT A GAME ====");
        for (int i = 0; i < games.size(); i++) {
            System.out.println((i + 1) + ". " + games.get(i).getGameName());
        }
        System.out.print("Enter your choice: ");
    }

    private void startSelectedGame(int gameIndex) {
        Game game = games.get(gameIndex);
        game.playGame();
        System.out.println("Game finished! Your score: " + game.getCurrentScore());

    }

    public int getUserChoice(int minOption, int maxOption) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= minOption && choice <= maxOption) {
                    return choice;
                } else if (minOption == maxOption) {
                    System.out.printf("Invalid choice. \nThere is only %d game in the list. \nPlease enter %d: ", minOption, maxOption);
                } else {
                    System.out.printf("Invalid choice. \nPlease enter a number between %d and %d: ",
                            minOption, maxOption);
                }

            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.printf("Invalid input. \nPlease enter a number between %d and %d: ",
                        minOption, maxOption);
            }
        }
    }

    private void addGame(Game game) {
        games.add(game);
    }
}
