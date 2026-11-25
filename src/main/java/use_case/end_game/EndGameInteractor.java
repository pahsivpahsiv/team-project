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

    public EndGameInteractor(
            EndGameOutputBoundary endGamePresenter,
            GameState gameState) {
        this.endGamePresenter = endGamePresenter;
        this.gameState = gameState;
    }

    @Override
    public void endGame(int id) {
        if (gameState.getStatus() == GameState.GameStatus.ENDED) {
            endGamePresenter.prepareFailView("The game has already ended.");
            return;
        }

        gameState.setStatus(GameState.GameStatus.ENDED);
        Player player = gameState.getPlayer(id);

        EndGameOutputData outputData = new EndGameOutputData(
                player.getId(),
                player.getScore(),
                gameState.getTime());

        endGamePresenter.prepareSuccessView(outputData);
    }
}
