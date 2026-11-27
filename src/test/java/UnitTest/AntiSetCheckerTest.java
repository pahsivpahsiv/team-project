package UnitTest;

import entity.Card;
import entity.CardStuff;
import org.junit.jupiter.api.Test;
import use_case.set_check.AntiSetChecker;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AntiSetCheckerTest {

    @Test
    void testCheckAntiSetTrue() {
        CardStuff cardStuff = new CardStuff();
        AntiSetChecker checker = new AntiSetChecker(cardStuff);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1);
        cards.add(2);
        cards.add(81);
        cards.add(42);
        assertTrue(checker.checkSet(cards));
    }
    @Test
    void testCheckAntiSetFalse() {
        CardStuff cardStuff = new CardStuff();
        AntiSetChecker checker = new AntiSetChecker(cardStuff);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1);
        cards.add(2);
        cards.add(81);
        cards.add(41);
        assertFalse(checker.checkSet(cards));
    }

    @Test
    void testCheckAntiSetOrder() {
        CardStuff cardStuff = new CardStuff();
        AntiSetChecker checker = new AntiSetChecker(cardStuff);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(42);
        cards.add(1);
        cards.add(2);
        cards.add(81);
        assertTrue(checker.checkSet(cards));
    }

}
