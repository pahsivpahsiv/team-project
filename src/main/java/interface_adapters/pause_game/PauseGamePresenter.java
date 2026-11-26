
package interface_adapters.pause_game;

import use_case.pause_game.PauseGameOutputBoundary;
import use_case.pause_game.PauseGameOutputData;

/**
 * Presenter for the Pause Game use case.
 */
public class PauseGamePresenter implements PauseGameOutputBoundary {

    private final PauseGameViewModel pauseGameViewModel;

    public PauseGamePresenter(PauseGameViewModel pauseGameViewModel) {
        this.pauseGameViewModel = pauseGameViewModel;
    }

    @Override
    public void prepareSuccessView(PauseGameOutputData outputData) {
        PauseGameState state = pauseGameViewModel.getState();

        state.setStatus(outputData.getStatus());
        state.setErrorMessage(null);

        pauseGameViewModel.notifyStateChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        PauseGameState state = pauseGameViewModel.getState();

        state.setErrorMessage(errorMessage);

        pauseGameViewModel.notifyStateChanged();
    }
}
