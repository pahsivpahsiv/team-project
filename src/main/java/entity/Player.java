package entity;

/**
 * A simple entity representing a Player. A Player has a Username representing their ID, a Score representing the player
 * points at each moment, and a Best-time representing the best record
 */


public class Player {
    private String username;
    private int score;
    private long bestTime;

    public Player(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty.");
        }

        this.username = username;
        this.score = 0;
        this.bestTime = -1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty.");
        }
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public long getBestTime() {
        return bestTime;
    }

    public void incrementScore() {
        score++;
    }

    public void resetScore() {
        score = 0;
    }

    public void updateBestTime(long newTime) {
        if (newTime < 0) {
            throw new IllegalArgumentException(
                    "Time cannot be negative.");
        }

        if (bestTime == -1 || newTime < bestTime) {
            bestTime = newTime;
        }
    }
}

