import java.util.HashMap;
import java.util.Scanner;

public class PlayerManager {
    private HashMap<String, Player> players;

    public PlayerManager() {
        players = new HashMap<>();
    }

    public Player createNewPlayer(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        while (!isValidUsername(username) || checkUsernameExists(username)) {
            if (!isValidUsername(username)) {
                System.out.println("Invalid username! Use letters and numbers only (3–15 characters).");
            } else {
                System.out.println("Username already exists. Try another.");
            }
            System.out.print("Enter username: ");
            username = scanner.nextLine().trim();
        }

        System.out.print("Enter display name: ");
        String displayName = scanner.nextLine().trim();
        if (displayName.isEmpty()) {
            displayName = username;
        }

        Player newPlayer = new Player(username, displayName);
        players.put(username, newPlayer);
        System.out.println("Player created successfully!");
        return newPlayer;
    }

    public boolean checkUsernameExists(String username) {
        return players.containsKey(username);
    }

    public boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9]{3,15}$");
    }

    public Player selectPlayer(Scanner scanner) {
        if (players.isEmpty()) {
            System.out.println("No players found. Create one first.");
            return null;
        }

        System.out.println("\n=== EXISTING PLAYERS ===");
        int i = 1;
        for (String username : players.keySet()) {
            System.out.println(i + ". " + username);
            i++;
        }
        System.out.print("Enter the number of the player: ");
        int choice = InputValidator.validateMenuChoice(scanner, 1, players.size());

        String selectedUsername = (String) players.keySet().toArray()[choice - 1];
        return players.get(selectedUsername);
    }
}
