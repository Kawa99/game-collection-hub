import java.util.ArrayList;
import java.util.Scanner;

public class GameHub {
    private ArrayList<Game> games;
    private Scanner scanner;
    private static final int MAIN_MENU_OPTIONS = 3;
    private static final int MINIMUM_CHOICE_NUMBER = 1;
    private PlayerManager playerManager;
    private Player currentPlayer;


    public GameHub() {
        games = new ArrayList<>();
        scanner = new Scanner(System.in);
        addGame(new RockPaperScissors(scanner));
        addGame(new NumberGuessingGame(scanner));
        addGame(new HangMan(scanner));
        playerManager = new PlayerManager();
    }

    public static void main(String[] args) {
        GameHub hub = new GameHub();
        hub.run();
    }

    private void run() {
        System.out.println("Welcome to GameHub!");
        System.out.println("1. Create New Player");
        System.out.println("2. Select Existing Player");
        System.out.print("\nEnter your choice: ");
        int profileChoice = InputValidator.validateMenuChoice(scanner, 1, 2);

        if (profileChoice == 1) {
            currentPlayer = playerManager.createNewPlayer(scanner);
        } else {
            currentPlayer = playerManager.selectPlayer(scanner);
            if (currentPlayer == null) {
                System.out.println("No players available. Creating new player...");
                currentPlayer = playerManager.createNewPlayer(scanner);
            }
        }
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
                            ErrorLogger.logError("Invalid game index selected", e);
                        } catch (Exception e) {
                            System.out.println("Error: Game crashed unexpectedly. Returning to menu." + e.getMessage());
                            ErrorLogger.logError("Game crashed unexpectedly", e);
                        }
                    }
                    break;
                case 2:
                    if (games.isEmpty()) {
                        System.out.println("No games available yet.\n");
                    } else {
                        displayGameMenu();
                        int gameChoice = getUserChoice(1, games.size());
                        try {
                            displayGameDescription(gameChoice - 1);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: Invalid selection." + e.getMessage());
                        }
                    break;
                    }
                case 3:
                    isRunning = false;
                    System.out.println("Thanks for playing! Goodbye!");
                    break;
            }
        }
    }

    private void displayMainMenu() {
        System.out.print("\n==== GAME HUB MAIN MENU ====");
        System.out.printf("     Username: %s\n", currentPlayer.getUsername());
        System.out.println("1. Play Games");
        System.out.println("2. View Rules");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void displayGameMenu() {
        System.out.print("\n==== SELECT A GAME ====");
        System.out.printf("     Username: %s\n", currentPlayer.getUsername());
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
        return InputValidator.validateMenuChoice(scanner, minOption, maxOption);
    }

    private void addGame(Game game) {
        games.add(game);
    }

    private void displayGameDescription(int gameIndex) {
        Game game = games.get(gameIndex);
        System.out.println("\n==== GAME RULES: " + game.getGameName() + " ====\n");
        System.out.println(game.getGameDescription());
        System.out.println("\nPress Enter to return to the menu...");
        scanner.nextLine();
    }
}
