package interface_adapters.end_game;

import use_case.end_game.EndGameInputBoundary;

/**
 * The controller for the End Game use case.
 */
public class EndGameController {

    private final EndGameInputBoundary endGameInteractor;


    public EndGameController(EndGameInputBoundary endGameInteractor) {

        this.endGameInteractor = endGameInteractor;
    }

    /**
     * Ends the game early. Called when the player clicks "End game".
     */
    public void endGame(int id) {
        endGameInteractor.endGame(id);
    }
}
