
package entity;

import java.util.Objects;

public class GameState {

    public enum GameStatus {
        PLAYING,
        PAUSED
    }

    public enum GameMode {
        SET,
        ANTI_SET,
        GHOST_SET
    }

    private long time;
    private CardStuff cardStuff;
    private GameStatus status;
    private GameMode mode;

    public GameState(CardStuff cardStuff, GameMode mode) {
        this.time = 0;
        this.cardStuff = Objects.requireNonNull(cardStuff);
        this.status = GameStatus.PLAYING;
        this.mode = Objects.requireNonNull(mode);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        if (time < 0) {
            throw new IllegalArgumentException(
                    "Time cannot be negative");
        }
        this.time = time;
    }

    public CardStuff getCardStuff() {
        return cardStuff;
    }

    public void setCardStuff(CardStuff cardStuff) {
        this.cardStuff = Objects.requireNonNull(cardStuff);
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = Objects.requireNonNull(status);
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = Objects.requireNonNull(mode);
    }
}
