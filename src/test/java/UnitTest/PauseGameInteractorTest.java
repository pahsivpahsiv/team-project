
package UnitTest;

import entity.CardStuff;
import entity.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.pause_game.PauseGameInteractor;
import use_case.pause_game.PauseGameOutputBoundary;
import use_case.pause_game.PauseGameOutputData;

import static org.junit.jupiter.api.Assertions.*;

class PauseGameInteractorTest {

    private GameState gameState;
    private TestPresenter presenter;
    private PauseGameInteractor interactor;

    @BeforeEach
    void setUp() {
        gameState = new GameState(
                new CardStuff(),
                GameState.GameMode.SET
        );

        presenter = new TestPresenter();
        interactor = new PauseGameInteractor(presenter, gameState);
    }

    @Test
    void testPauseGame() {
        interactor.pause();

        assertEquals(GameState.GameStatus.PAUSED,
                gameState.getStatus());
        assertNotNull(presenter.outputData);
        assertEquals(GameState.GameStatus.PAUSED,
                presenter.outputData.getStatus());
        assertNull(presenter.errorMessage);
    }

    @Test
    void testResumeGame() {
        gameState.setStatus(GameState.GameStatus.PAUSED);

        interactor.resume();

        assertEquals(GameState.GameStatus.PLAYING,
                gameState.getStatus());
        assertNotNull(presenter.outputData);
        assertEquals(GameState.GameStatus.PLAYING,
                presenter.outputData.getStatus());
        assertNull(presenter.errorMessage);
    }

    @Test
    void testPauseAlreadyPausedGame() {
        gameState.setStatus(GameState.GameStatus.PAUSED);

        interactor.pause();

        assertEquals(GameState.GameStatus.PAUSED,
                gameState.getStatus());
        assertNull(presenter.outputData);
        assertEquals("The game is already paused.",
                presenter.errorMessage);
    }

    @Test
    void testResumeAlreadyPlayingGame() {
        interactor.resume();

        assertEquals(GameState.GameStatus.PLAYING,
                gameState.getStatus());
        assertNull(presenter.outputData);
        assertEquals("The game is already playing.",
                presenter.errorMessage);
    }

    /**
     * Fake presenter used to capture the interactor's output.
     */
    private static class TestPresenter
            implements PauseGameOutputBoundary {

        private PauseGameOutputData outputData;
        private String errorMessage;

        @Override
        public void prepareSuccessView(PauseGameOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
