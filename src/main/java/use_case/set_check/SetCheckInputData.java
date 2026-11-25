package use_case.set_check;

import java.util.ArrayList;

/**
 * Input data from the controller to the use case.
 */
public class SetCheckInputData {

    /**
     * The cards chosen by player on the field.
     */
    private final ArrayList<Integer> cards;

    /**
     * The id of the player who chose the cards.
     */
    private final int id;

    public SetCheckInputData(ArrayList<Integer> cards, int id) {
        this.cards = cards;
        this.id = id;
    }

    public ArrayList<Integer> getCards() {
        return this.cards;
    }

    public int getId() {
        return id;
    }
}
