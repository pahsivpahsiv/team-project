package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * A entity representing all 81 unique cards for a game of Set.
 */
public class CardStuff {

    /**
     * A list of all cards in the game saved by id.
     */
    private final HashMap<Integer, Card> cards;

    /**
     * A list of all cards in this deck by their id.
     */
    private final ArrayList<Integer> deck;

    /**
     * A list of all cards currently on the field by their id.
     */
    private final ArrayList<Integer> field;

    public CardStuff() {
        int cardCounter = 1;
        HashMap<Integer, Card> cards = new HashMap<>();
        ArrayList<Integer> deck = new ArrayList<>();
        ArrayList<Integer> field = new ArrayList<>();
        for (int i = 1; i <= 3; i++){
            for (int j = 1; j <= 3; j++){
                for (int l = 1; l <= 3; l++){
                    for (int m = 1; m <= 3; m++) {
                        int[] characteristics = {i, j, l, m};
                        Card card = new Card(cardCounter, characteristics);
                        cards.put(cardCounter, card);
                        deck.add(cardCounter);
                        cardCounter++;
                    }
                }
            }
        }
        Collections.shuffle(deck);
        for (int i = 0; i < 12; i++){
            field.add(deck.remove(i));
        }
        this.cards = cards;
        this.deck = deck;
        this.field = field;
    }

    /**
     * Returns the cards currently in the deck.
     */
    public ArrayList<Integer> getDeck() {
        return deck;
    }

    /**
     * Returns the cards currently on the field.
     */
    public ArrayList<Integer> getField() {
        return field;
    }

    /**
     * Returns the cards associated with given ids.
     */
    public ArrayList<Card> getCards(ArrayList<Integer> ids) {
        ArrayList<Card> output = new ArrayList<>();
        for (Integer id : ids){
            output.add(this.cards.get(id));
        }
        return output;
    }

    /**
     * Replaces given cards on the field with cards from the deck, returns the new field,
     * and throws an exception if a card  is not present on the field.
     */
    public ArrayList<Integer> replaceCards(ArrayList<Integer> ids) {
        for (int id : ids) {
            if (!field.contains(id)) {
                throw new InvalidCardException("This card is not on the field and " +
                        "cannot be replaced.");
            }
        }

        if (deck.isEmpty()) {
            for (int id : ids) {
                field.remove(id);
            }
        } else if (field.size() > 12) {
            while (field.size() > 12) {
                field.remove(ids.get(0));
                ids.remove(ids.get(0));
            }
            if (!ids.isEmpty()) {
                for (int id : ids) {
                    field.remove(id);
                    if (!deck.isEmpty()) {
                        field.add(deck.remove(0));
                    }
                }
            }
        } else if (ids.size() > deck.size()) {
            for (int id : ids) {
                field.remove(id);
                if (!deck.isEmpty()) {
                    field.add(deck.remove(0));
                }
            }
        } else {
            for (int id : ids) {
                field.remove(id);
                field.add(deck.remove(0));
            }
        }
        return field;
    }

    /**
     * Expands the field with at most three cards from the deck, returns the new field,
     * and throws an exception if the deck is empty.
     */
    public ArrayList<Integer> expandField() {
        if (deck.isEmpty()) {
            throw new EmptyDeckException("The deck is empty and cards cannot be added.");
        }

        if (field.size() > 12) {
            throw new ExpandedFieldException("The field has been expanded already.");
        }

        while (field.size() < 15) {
            if (deck.isEmpty()) {
                break;
            }
            field.add((deck.remove(0)));
        }
        return field;
    }
}

class EmptyDeckException extends RuntimeException {
    public EmptyDeckException(String m) {
        super(m);
    }
}

class ExpandedFieldException extends RuntimeException {
    public ExpandedFieldException(String m) {
        super(m);
    }
}

class InvalidCardException extends RuntimeException {
    public InvalidCardException(String m) {
        super(m);
    }
}

