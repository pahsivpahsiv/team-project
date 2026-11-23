package UnitTest;

import entity.CardStuff;
import org.junit.jupiter.api.Test;
import use_case.no_sets.NoSetsInteractor;
import use_case.no_sets.NoSetsOutputBoundary;
import use_case.no_sets.NormalSetChecker;

public class NoSetsInteractorTest {

    @Test
    void testExecute() {
        CardStuff cardStuff = new CardStuff();
        NoSetsOutputBoundary presenter = new TestPresenter();
        NormalSetChecker setChecker = new NormalSetChecker(cardStuff);
        NoSetsInteractor interactor = new NoSetsInteractor(presenter, cardStuff, setChecker);
        interactor.execute(cardStuff.getField());
    }

}
