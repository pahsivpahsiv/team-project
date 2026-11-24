
package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameState {

    public enum GameStatus {
        PLAYING,
        PAUSED,
        ENDED
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
    private HashMap<String, Player> players;

    public GameState(CardStuff cardStuff, GameMode mode, Player[] players) {
        this.time = 0;
        this.cardStuff = Objects.requireNonNull(cardStuff);
        this.status = GameStatus.PLAYING;
        this.mode = Objects.requireNonNull(mode);
        HashMap<String, Player> hashPlayers = new HashMap<>();
        for (Player player : players) {
            hashPlayers.put(player.getUsername(), player);
        }
        this.players = hashPlayers;
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

    public Player getPlayer(String username) { 
        return this.players.get(username); 
    }
}
