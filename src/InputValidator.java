import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {

    public static int validateIntegerInput(Scanner scanner) {
        while (true) {
            try {
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    public static int validMenueChoice(Scanner scanner, int min, int max) {
        while (true) {
            int input = validateIntegerInput(scanner);
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.printf("Invalid input. Please enter a valid integer between %d and %d: ", min, max);
            }
        }
    }
}
