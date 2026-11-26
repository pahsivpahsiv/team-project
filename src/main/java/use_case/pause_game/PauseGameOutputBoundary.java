
package use_case.pause_game;

/**
 * Output boundary for the Pause Game use case.
 */
public interface PauseGameOutputBoundary {

    /**
     * Called when the game is successfully paused or resumed.
     *
     * @param outputData the updated game status
     */
    void prepareSuccessView(PauseGameOutputData outputData);

    /**
     * Called when the requested operation cannot be performed.
     *
     * @param errorMessage the reason for failure
     */
    void prepareFailView(String errorMessage);
}
