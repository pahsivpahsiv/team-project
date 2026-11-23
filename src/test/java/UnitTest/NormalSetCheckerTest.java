package UnitTest;

import entity.CardStuff;
import org.junit.jupiter.api.Test;
import use_case.no_sets.NormalSetChecker;
import use_case.no_sets.SetCheckerStrategy;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NormalSetCheckerTest {

    @Test
    void testCheckSet() {
        CardStuff cardStuff = new CardStuff();
        SetCheckerStrategy setChecker = new NormalSetChecker(cardStuff);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(4);
        assertFalse(setChecker.checkSet(ids));
        ids.remove(2);
        ids.add(3);
        assertTrue(setChecker.checkSet(ids));
    }
}
