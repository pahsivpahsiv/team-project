package interface_adapters.end_game;

import use_case.end_game.EndGameOutputBoundary;
import use_case.end_game.EndGameOutputData;

/**
 * The presenter for the End Game use case.
 */
public class EndGamePresenter implements EndGameOutputBoundary {

    private final EndGameViewModel endGameViewModel;

    public EndGamePresenter(EndGameViewModel endGameViewModel) {
        this.endGameViewModel = endGameViewModel;
    }

    @Override
    public void prepareSuccessView(EndGameOutputData outputData) {
        EndGameState state = endGameViewModel.getState();
        state.setId(outputData.getId());
        state.setScore(outputData.getScore());
        state.setTimePlayed(outputData.getTimePlayed());
        state.setErrorMessage(null);
        endGameViewModel.notifyStateChanged();
    }

    @Override
    public void prepareFailView(String message) {
        endGameViewModel.getState().setErrorMessage(message);
        endGameViewModel.notifyStateChanged();
    }
}
