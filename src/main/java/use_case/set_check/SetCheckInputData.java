package use_case.set_check;

import java.util.ArrayList;

public class SetCheckInputData {

    private final ArrayList<Integer> cards;
    private final String username;

    public SetCheckInputData(ArrayList<Integer> cards, String username) {
        this.cards = cards;
        this.username = username;
    }

    public ArrayList<Integer> getCards() {
        return this.cards;
    }

    public String getUsername() {
        return username;
    }
}
