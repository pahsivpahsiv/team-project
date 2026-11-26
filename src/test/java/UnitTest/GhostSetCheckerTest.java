package UnitTest;

import entity.Card;
import entity.CardStuff;
import org.junit.jupiter.api.Test;
import use_case.set_check.GhostSetChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GhostSetCheckerTest {

    @Test
    void testCheckGhostSetTrue() {
        CardStuff cardStuff = new CardStuff();
        GhostSetChecker checker = new GhostSetChecker(cardStuff);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1);
        cards.add(41);
        cards.add(81);

        cards.add(5);
        cards.add(45);
        cards.add(73);
        assertTrue(checker.checkSet(cards));
    }

    @Test
    void testCheckGhostSetTrueOrder() {
        CardStuff cardStuff = new CardStuff();
        GhostSetChecker checker = new GhostSetChecker(cardStuff);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1);
        cards.add(41);
        cards.add(81);

        cards.add(5);
        cards.add(45);
        cards.add(73);
        for (int i = 0; i < 1000; i++) {
            Collections.shuffle(cards);
            assertTrue(checker.checkSet(cards));
        }
    }

    @Test
    void testCheckGhostSetFalse() {
        CardStuff cardStuff = new CardStuff();
        GhostSetChecker checker = new GhostSetChecker(cardStuff);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1);
        cards.add(41);
        cards.add(80);

        cards.add(5);
        cards.add(45);
        cards.add(73);
        assertFalse(checker.checkSet(cards));
    }

    @Test
    void testCheckGhostSetFalseOrder() {
        CardStuff cardStuff = new CardStuff();
        GhostSetChecker checker = new GhostSetChecker(cardStuff);
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1);
        cards.add(41);
        cards.add(80);

        cards.add(5);
        cards.add(45);
        cards.add(73);
        for (int i = 0; i < 1000; i++) {
            Collections.shuffle(cards);
            assertFalse(checker.checkSet(cards));
        }
    }
}
