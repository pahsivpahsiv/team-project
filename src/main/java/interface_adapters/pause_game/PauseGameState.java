
package interface_adapters.pause_game;

import entity.GameState;

/**
 * Stores the pause status displayed by the game UI.
 */
public class PauseGameState {

    private GameState.GameStatus status =
            GameState.GameStatus.PLAYING;

    private String errorMessage;

    public GameState.GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameState.GameStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
