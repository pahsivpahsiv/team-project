package UnitTest;

import entity.CardStuff;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CardStuffTest {

    @Test
    void testConstructor() {
        CardStuff cardStuff = new CardStuff();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(12);
        ids.add(81);
        assertEquals(69, cardStuff.getDeck().size());
        assertEquals(12, cardStuff.getField().size());
        assertEquals(1, cardStuff.getCards(ids).get(0).getId());
    }

    @Test
    void testReplaceCards() {
        CardStuff cardStuff = new CardStuff();
        System.out.println(cardStuff.getField());
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(cardStuff.getField().get(0));
        ids.add(cardStuff.getField().get(1));
        ids.add(cardStuff.getField().get(2));
        ArrayList<Integer> cards = cardStuff.replaceCards(ids);
        System.out.println(cardStuff.getField());
        assertFalse(cardStuff.getField().contains(ids));
        assertEquals(cardStuff.getField(), cards);

        cardStuff.expandField();
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(cardStuff.getField().get(0));
        integers.add(cardStuff.getField().get(1));
        integers.add(cardStuff.getField().get(2));
        cardStuff.replaceCards(integers);

        assertEquals(12, cardStuff.getField().size());

    }

    @Test
    void testExpandField() {
        CardStuff cardStuff = new CardStuff();
        ArrayList<Integer> integers = cardStuff.expandField();
        assertEquals(15, integers.size());

        Exception exception = assertThrows(RuntimeException.class, cardStuff::expandField);
        String expectedMessage = "The field has been expanded already.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testEmptyDeck() {
        CardStuff cardStuff = new CardStuff();
        while (!cardStuff.getDeck().isEmpty()) {
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(cardStuff.getField().get(0));
            ids.add(cardStuff.getField().get(1));
            ids.add(cardStuff.getField().get(2));
            cardStuff.replaceCards(ids);
        }
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(cardStuff.getField().get(0));
        ids.add(cardStuff.getField().get(1));
        ids.add(cardStuff.getField().get(2));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            cardStuff.replaceCards(ids);
        });
        String expectedMessage = "The deck is empty and the cards cannot be replaced.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        Exception exceptionTwo = assertThrows(RuntimeException.class, cardStuff::expandField);
        String expectedMessageTwo = "The deck is empty and cards cannot be added.";
        String actualMessageTwo = exceptionTwo.getMessage();

        assertTrue(actualMessageTwo.contains(expectedMessageTwo));

    }
}
