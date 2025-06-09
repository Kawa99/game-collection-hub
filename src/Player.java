import java.time.LocalDate;
import java.util.HashMap;

public class Player {
    private String username;
    private String displayName;
    private LocalDate joinDate;
    private HashMap<String, Integer> gameScores;

    public Player(String username, String displayName) {
        this.username = username;
        this.displayName = displayName;
        this.joinDate = LocalDate.now(); // set to current date
        this.gameScores = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setScore(String gameName, int score) {
        gameScores.put(gameName, score);
    }

    public Integer getScore(String gameName) {
        return gameScores.getOrDefault(gameName, 0);
    }

    public HashMap<String, Integer> getAllScores() {
        return gameScores;
    }
}
