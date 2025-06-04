import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangMan extends Game{
    private final static int MAX_ATTEMPTS = 6;
    private int currentScore = 0;

    public HangMan(Scanner scanner) {
        super("Hangman", "Guess the letters in the word or the man is hanged!", scanner);
    }

    public void playGame() {
        currentScore = 0;
        String filePath = "hangmanWords.txt";
        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("I/O exception");
        }

        if (words.isEmpty()) {
            System.out.println("Could not load words file. Game cannot start.");
            return;
        }

        Random random = new Random();

        String word = words.get(random.nextInt(words.size()));

        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for (int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }
        System.out.println("\n===================");
        System.out.println("Welcome to HangMan!");
        System.out.println("===================");

        while (wrongGuesses < MAX_ATTEMPTS) {

            System.out.println(getHangmanArt(wrongGuesses));
            System.out.print("Word: ");

            for (char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (word.indexOf(guess) >= 0) {
                System.out.println("You guessed correctly!\n");

                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordState.set(i, guess);
                        currentScore++;
                    }
                }

                if (!wordState.contains('_')) {
                    System.out.println(getHangmanArt(wrongGuesses));
                    System.out.println("You Win!");
                    System.out.println("The word was: " + word);
                    currentScore++;
                    break;
                }
                System.out.println("Your score was: " + currentScore);
            } else {
                wrongGuesses++;
                System.out.println("You guessed incorrectly!\n");
            }

            if (wrongGuesses >= MAX_ATTEMPTS) {
                System.out.println(getHangmanArt(wrongGuesses));
                System.out.println("Game Over!");
                System.out.println("The word was: " + word);
            }
        }
    }

    static String getHangmanArt(int wrongGuesses) {
        return switch (wrongGuesses) {
            case 0 -> """
                      
                      
                      
                      """;
            case 1 -> """
                       O
                    
                      """;
            case 2 -> """
                       O
                       |
                      
                      """;
            case 3 -> """
                       O
                      /|
                      
                      """;
            case 4 -> """
                       O
                      /|\\
                      
                      """;
            case 5 -> """
                       O
                      /|\\
                      /
                      """;
            case 6 -> """
                       O
                      /|\\
                      / \\
                      """;
            default -> "";
        };
    }

    public int getCurrentScore() {
        return currentScore;
    }
}
