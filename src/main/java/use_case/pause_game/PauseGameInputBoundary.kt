package use_case.pause_game;

/**
 * Input boundary for the Pause Game use case.
 */
interface PauseGameInputBoundary {
    /**
     * Pauses the current game.
     */
    fun pause()

    /**
     * Resumes the current game.
     */
    fun resume()
}