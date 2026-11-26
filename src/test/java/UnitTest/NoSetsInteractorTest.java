package UnitTest;

import entity.Card;
import entity.CardStuff;
import entity.GameState;
import entity.Player;
import org.junit.jupiter.api.Test;
import use_case.no_sets.NoSetsInteractor;
import use_case.no_sets.NoSetsOutputBoundary;
import use_case.no_sets.NormalSetChecker;
import use_case.no_sets.SetsOutputData;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NoSetsInteractorTest {
    private static class RecordingPresenter implements NoSetsOutputBoundary {
        SetsOutputData successData;
        String failMessage;

        @Override
        public void prepareSuccessView(SetsOutputData outputData) {
            successData = outputData;
        }

        @Override
        public void prepareFailView(String message) {
            failMessage = message;
        }
    }

    @Test
    void testExecuteSuccess() {
        CardStuff cardStuff = new CardStuff();
        Player player = new Player("alice", 1);
        GameState gameState = new GameState(cardStuff, GameState.GameMode.SET, new Player[] {player});
        RecordingPresenter presenter = new RecordingPresenter();
        NormalSetChecker setChecker = new NormalSetChecker(cardStuff);
        NoSetsInteractor interactor = new NoSetsInteractor(presenter, gameState, setChecker);
        ArrayList<Integer> fakeField = new ArrayList<>();

        fakeField.add(1);
        fakeField.add(41);
        fakeField.add(1);
        fakeField.add(41);

        fakeField.add(11);
        fakeField.add(11);
        fakeField.add(37);
        fakeField.add(37);

        fakeField.add(61);
        fakeField.add(61);
        fakeField.add(79);
        fakeField.add(79);

        interactor.execute(fakeField);
        assertNull(presenter.failMessage);
        assertNotNull(presenter.successData);
        assertEquals(15, presenter.successData.getField().size());
    }

    @Test
    void testExecuteFail() {
        CardStuff cardStuff = new CardStuff();
        Player player = new Player("alice", 1);
        GameState gameState = new GameState(cardStuff, GameState.GameMode.SET, new Player[] {player});
        RecordingPresenter presenter = new RecordingPresenter();
        NormalSetChecker setChecker = new NormalSetChecker(cardStuff);
        NoSetsInteractor interactor = new NoSetsInteractor(presenter, gameState, setChecker);

        interactor.execute(cardStuff.getDeck());
        assertNull(presenter.successData);
        assertNotNull(presenter.failMessage);
    }

}
