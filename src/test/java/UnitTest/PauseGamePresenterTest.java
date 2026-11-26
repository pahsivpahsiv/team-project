
package UnitTest;

import entity.GameState;
import interface_adapters.pause_game.PauseGamePresenter;
import interface_adapters.pause_game.PauseGameState;
import interface_adapters.pause_game.PauseGameViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.pause_game.PauseGameOutputData;

import static org.junit.jupiter.api.Assertions.*;

class PauseGamePresenterTest {

    private PauseGameViewModel viewModel;
    private PauseGamePresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new PauseGameViewModel();
        presenter = new PauseGamePresenter(viewModel);
    }

    @Test
    void testPrepareSuccessViewPaused() {
        PauseGameOutputData outputData =
                new PauseGameOutputData(
                        GameState.GameStatus.PAUSED);

        presenter.prepareSuccessView(outputData);

        PauseGameState state = viewModel.getState();

        assertEquals(GameState.GameStatus.PAUSED,
                state.getStatus());
        assertNull(state.getErrorMessage());
    }

    @Test
    void testPrepareSuccessViewPlaying() {
        viewModel.getState().setStatus(
                GameState.GameStatus.PAUSED);

        PauseGameOutputData outputData =
                new PauseGameOutputData(
                        GameState.GameStatus.PLAYING);

        presenter.prepareSuccessView(outputData);

        assertEquals(GameState.GameStatus.PLAYING,
                viewModel.getState().getStatus());
        assertNull(viewModel.getState().getErrorMessage());
    }

    @Test
    void testPrepareFailView() {
        presenter.prepareFailView(
                "The game is already paused.");

        assertEquals("The game is already paused.",
                viewModel.getState().getErrorMessage());

        assertEquals(GameState.GameStatus.PLAYING,
                viewModel.getState().getStatus());
    }

    @Test
    void testSuccessClearsPreviousError() {
        presenter.prepareFailView("Previous error");

        presenter.prepareSuccessView(
                new PauseGameOutputData(
                        GameState.GameStatus.PAUSED));

        assertNull(viewModel.getState().getErrorMessage());
    }

    @Test
    void testListenerNotified() {
        final int[] notificationCount = {0};

        viewModel.addListener(
                () -> notificationCount[0]++);

        presenter.prepareSuccessView(
                new PauseGameOutputData(
                        GameState.GameStatus.PAUSED));

        assertEquals(1, notificationCount[0]);
    }
}
