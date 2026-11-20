package UnitTest;

import entity.Card;
import entity.CardStuff;
import org.junit.jupiter.api.Test;
import use_case.no_sets.NoSetsInteractor;
import use_case.no_sets.NoSetsOutputBoundary;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NoSetsInteractorTest {

    @Test
    void testExecute() {
        CardStuff cardStuff = new CardStuff();
        NoSetsOutputBoundary presenter = new TestPresenter();
        NoSetsInteractor interactor = new NoSetsInteractor(presenter, cardStuff);
        interactor.execute(cardStuff.getField());
    }

    @Test
    void testCheckSet() {
        CardStuff cardStuff = new CardStuff();
        NoSetsOutputBoundary presenter = new TestPresenter();
        NoSetsInteractor interactor = new NoSetsInteractor(presenter, cardStuff);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(4);
        assertFalse(interactor.checkSet(ids));
    }
}
