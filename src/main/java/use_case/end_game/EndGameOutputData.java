package use_case.end_game;

/**
 * Output data for the End Game use case
 */
public class EndGameOutputData {

    private final String username;
    private final int score;
    private final long timePlayed;

    public EndGameOutputData(String username, int score, long timePlayed) {
        this.username = username;
        this.score = score;
        this.timePlayed = timePlayed;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public long getTimePlayed() {
        return timePlayed;
    }
}
