package use_case.pause_game;

/**
 * Input boundary for the Pause Game use case.
 */
public interface PauseGameInputBoundary {
    /**
     * Pauses the current game.
     */
    void pause();

    /**
     * Resumes the current game.
     */
    void resume();
}