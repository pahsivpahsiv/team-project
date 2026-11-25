package use_case.end_game;

/**
 * Output data for the End Game use case
 */
public class EndGameOutputData {

    private final int id;
    private final int score;
    private final long timePlayed;

    public EndGameOutputData(int id, int score, long timePlayed) {
        this.id = id;
        this.score = score;
        this.timePlayed = timePlayed;
    }

    public String getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public long getTimePlayed() {
        return timePlayed;
    }
}
