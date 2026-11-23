package use_case.end_game;

import entity.GameState;
import entity.Player;

/**
 * Lets a player stop the current game early
 * and see their results.
 */
public class EndGameInteractor implements EndGameInputBoundary {

    private final EndGameOutputBoundary endGamePresenter;
    private final GameState gameState;
    private final Player player;

    public EndGameInteractor(
            EndGameOutputBoundary endGamePresenter,
            GameState gameState,
            Player player) {
        this.endGamePresenter = endGamePresenter;
        this.gameState = gameState;
        this.player = player;
    }

    @Override
    public void endGame() {
        if (gameState.getStatus() == GameState.GameStatus.ENDED) {
            endGamePresenter.prepareFailView("The game has already ended.");
            return;
        }

        gameState.setStatus(GameState.GameStatus.ENDED);

        EndGameOutputData outputData = new EndGameOutputData(
                player.getUsername(),
                player.getScore(),
                gameState.getTime());

        endGamePresenter.prepareSuccessView(outputData);
    }
}
