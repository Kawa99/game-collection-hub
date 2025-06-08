import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ErrorLogger {

    private static final String LOG_FILE = "error_log.txt";

    public static void logError(String message, Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("[" + LocalDateTime.now() + "] ERROR: " + message);
            if (e != null) {
                writer.println("Exception: " + e.getMessage());
                for (StackTraceElement element : e.getStackTrace()) {
                    writer.println("\tat " + element);
                }
            }
            writer.println();
        } catch (IOException ioException) {
            System.out.println("Failed to write to log file.");
        }
    }
}
