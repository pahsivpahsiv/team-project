package use_case.end_game;

/**
 * Output boundary for the End Game use case.
 */
public interface EndGameOutputBoundary {

    /**
     * Prepares the view shown if the game has been successfully ended.
     */
    void prepareSuccessView(EndGameOutputData outputData);

    /**
     * Prepares the view shown when the game could not be ended.
     */
    void prepareFailView(String message);
}
