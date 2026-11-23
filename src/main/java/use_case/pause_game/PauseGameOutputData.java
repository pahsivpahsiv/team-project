
package use_case.pause_game;

import entity.GameState;

/**
 * Output data for the Pause Game use case.
 */
public class PauseGameOutputData {

    private final GameState.GameStatus status;

    /**
     * Constructs the output data with the updated game status.
     *
     * @param status the updated game status
     */
    public PauseGameOutputData(GameState.GameStatus status) {
        this.status = status;
    }

    /**
     * Returns the updated game status.
     *
     * @return the current game status
     */
    public GameState.GameStatus getStatus() {
        return status;
    }
}
