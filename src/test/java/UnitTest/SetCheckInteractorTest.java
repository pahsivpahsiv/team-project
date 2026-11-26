package UnitTest;

import entity.CardStuff;
import org.junit.jupiter.api.Test;
import use_case.set_check.SetCheckInteractor;
import use_case.no_sets.NoSetsOutputBoundary;
import use_case.no_sets.NormalSetChecker;
import use_case.no_sets.SetCheckerStrategy;
import use_case.set_check.SetCheckOutputBoundary;

import java.util.ArrayList;

public class SetCheckInteractorTest {
    @Test
    void testExecute() {
        CardStuff cardStuff = new CardStuff();
        SetCheckOutputBoundary presenter = new SetCheckTestPresenter();
        NormalSetChecker setChecker = new NormalSetChecker(cardStuff);
        SetCheckInteractor interactor = new SetCheckInteractor(presenter, cardStuff, setChecker);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(cardStuff.getField().get(0));
        cards.add(cardStuff.getField().get(1));
        cards.add(cardStuff.getField().get(2));
        System.out.println(setChecker.checkSet(cards));
        interactor.execute(cards);
    }
}
