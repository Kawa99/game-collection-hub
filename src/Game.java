import java.util.Scanner;

/**
 * Abstract base class for all games in the Game Collection Hub.
 * Provides common functionality and defines the contract that
 * all games must implement.
 */
public abstract class Game {
    protected String gameName;
    protected String gameDescription;
    protected Scanner scanner;

    /**
     * Creates a new game with the specified name, description, and scanner.
     *
     * @param gameName the display name of the game
     * @param gameDescription brief instructions on how to play the game
     * @param scanner the shared Scanner instance from the game hub
     */
    public Game(String gameName, String gameDescription, Scanner scanner) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.scanner = scanner;
    }

    /**
     * Returns the name of this game.
     *
     * @return the game's display name
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Returns the description and rules for this game.
     *
     * @return instructions on how to play the game
     */
    public String getGameDescription() {
        return gameDescription;
    }

    /**
     * Plays the game from start to finish.
     * Implementation varies by game type.
     */
    public abstract void playGame();

    /**
     * Returns the score from the current or most recently played game.
     *
     * @return the player's score as an integer
     */
    public abstract int getCurrentScore();
}
