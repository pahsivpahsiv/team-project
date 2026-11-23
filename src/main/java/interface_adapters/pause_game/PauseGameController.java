
package interface_adapters.pause_game;

import use_case.pause_game.PauseGameInputBoundary;

/**
 * The controller for the Pause Game use case.
 */
public class PauseGameController {

    private final PauseGameInputBoundary pauseGameInteractor;

    /**
     * Constructs the controller.
     *
     * @param pauseGameInteractor the input boundary
     */
    public PauseGameController(
            PauseGameInputBoundary pauseGameInteractor) {
        this.pauseGameInteractor = pauseGameInteractor;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        pauseGameInteractor.pause();
    }

    /**
     * Resumes the game.
     */
    public void resume() {
        pauseGameInteractor.resume();
    }
}
