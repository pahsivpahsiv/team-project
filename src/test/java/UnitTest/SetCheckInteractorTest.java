package UnitTest;

import entity.CardStuff;
import entity.GameState;
import entity.Player;
import org.junit.jupiter.api.Test;
import use_case.no_sets.SetsOutputData;
import use_case.set_check.SetCheckInputData;
import use_case.set_check.SetCheckInteractor;
import use_case.no_sets.NoSetsOutputBoundary;
import use_case.no_sets.NormalSetChecker;
import use_case.no_sets.SetCheckerStrategy;
import use_case.set_check.SetCheckOutputBoundary;
import use_case.set_check.SetCheckOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SetCheckInteractorTest {

    private static class RecordingPresenter implements SetCheckOutputBoundary {
        SetCheckOutputData successData;
        String failMessage;

        @Override
        public void prepareSuccessView(SetCheckOutputData outputData) {
            successData = outputData;
        }

        @Override
        public void prepareFailView(String message) {
            failMessage = message;
        }
    }
    @Test
    void testExecute() {
        CardStuff cardStuff = new CardStuff();
        RecordingPresenter presenter = new RecordingPresenter();
        Player player = new Player("alice", 1);
        GameState gameState = new GameState(cardStuff, GameState.GameMode.SET,
                new Player[] {player});
        NormalSetChecker setChecker = new NormalSetChecker(cardStuff);
        SetCheckInteractor interactor = new SetCheckInteractor(presenter, gameState);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(cardStuff.getField().get(0));
        cards.add(cardStuff.getField().get(1));
        cards.add(cardStuff.getField().get(2));
        SetCheckInputData input = new SetCheckInputData(cards, 1);
        interactor.execute(input);
        if (setChecker.checkSet(cards)) {
            assertNotNull(presenter.successData);
            assertNull(presenter.failMessage);
            assertEquals(1, gameState.getPlayer(1).getScore());
            assertEquals(3, presenter.successData.getAdded().size());
        } else {
            assertNotNull(presenter.failMessage);
            assertNull(presenter.successData);
            assertEquals("This is not a set.", presenter.failMessage);
        }
    }
}
