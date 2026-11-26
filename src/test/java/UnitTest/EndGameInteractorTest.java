package UnitTest;

import entity.CardStuff;
import entity.GameState;
import entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.end_game.EndGameInteractor;
import use_case.end_game.EndGameOutputBoundary;
import use_case.end_game.EndGameOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class EndGameInteractorTest {

    /** A presenter that records what the interactor sent it, so tests can assert on it. */
    private static class RecordingPresenter implements EndGameOutputBoundary {
        EndGameOutputData successData;
        String failMessage;

        @Override
        public void prepareSuccessView(EndGameOutputData outputData) {
            successData = outputData;
        }

        @Override
        public void prepareFailView(String message) {
            failMessage = message;
        }
    }

    private GameState gameState;
    private Player player;
    private RecordingPresenter presenter;
    private EndGameInteractor interactor;

    @BeforeEach
    void setUp() {
        player = new Player("alice", 1);
        gameState = new GameState(new CardStuff(), GameState.GameMode.SET, new Player[] {player});
        gameState.setTime(120);
        player.incrementScore();
        player.incrementScore();
        presenter = new RecordingPresenter();
        interactor = new EndGameInteractor(presenter, gameState);
    }

    @Test
    void endingAGameInProgressShowsResults() {
        interactor.endGame(1);

        assertEquals(GameState.GameStatus.ENDED, gameState.getStatus());
        assertNull(presenter.failMessage);
        assertNotNull(presenter.successData);
        assertEquals(1, presenter.successData.getId());
        assertEquals(2, presenter.successData.getScore());
        assertEquals(120, presenter.successData.getTimePlayed());
    }

    @Test
    void aPausedGameCanBeEnded() {
        gameState.setStatus(GameState.GameStatus.PAUSED);

        interactor.endGame(1);

        assertEquals(GameState.GameStatus.ENDED, gameState.getStatus());
        assertNotNull(presenter.successData);
    }

    @Test
    void endingAnAlreadyEndedGameFails() {
        gameState.setStatus(GameState.GameStatus.ENDED);

        interactor.endGame(1);

        assertNull(presenter.successData);
        assertNotNull(presenter.failMessage);
    }

    @Test
    void endingEarlyDoesNotSetABestTime() {
        // I've assumed that a game ended early is not recorded as a best time.
        interactor.endGame(1);

        assertEquals(-1, player.getBestTime());
    }
}
