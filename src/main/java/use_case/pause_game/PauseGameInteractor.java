
package use_case.pause_game;

import entity.GameState;

public class PauseGameInteractor implements PauseGameInputBoundary {

    private final GameState gameState;
    private final PauseGameOutputBoundary pauseGamePresenter;

    public PauseGameInteractor(
            PauseGameOutputBoundary pauseGamePresenter,
            GameState gameState) {
        this.pauseGamePresenter = pauseGamePresenter;
        this.gameState = gameState;
    }

    @Override
    public void pause() {
        if (gameState.getStatus() == GameState.GameStatus.PAUSED) {
            pauseGamePresenter.prepareFailView(
                    "The game is already paused.");
            return;
        }

        gameState.setStatus(GameState.GameStatus.PAUSED);

        PauseGameOutputData outputData =
                new PauseGameOutputData(gameState.getStatus());

        pauseGamePresenter.prepareSuccessView(outputData);
    }

    @Override
    public void resume() {
        if (gameState.getStatus() != GameState.GameStatus.PAUSED) {
            pauseGamePresenter.prepareFailView(
                    "The game is not paused.");
            return;
        }

        gameState.setStatus(GameState.GameStatus.PLAYING);

        PauseGameOutputData outputData =
                new PauseGameOutputData(gameState.getStatus());

        pauseGamePresenter.prepareSuccessView(outputData);
    }
}
