package use_case.no_sets;

import entity.Card;
import entity.CardStuff;

import java.util.ArrayList;

/**
 * Implements SetCheckerStrategy for normal Set mode.
 */
public class NormalSetChecker implements SetCheckerStrategy {

    private final CardStuff cardStuff;
    public NormalSetChecker(CardStuff cardStuff){
        this.cardStuff = cardStuff;
    }

    @Override
    public boolean checkSet(ArrayList<Integer> ids) {
        ArrayList<Card> cards = cardStuff.getCards(ids);
        boolean match = true;

        int[] chars1 = cards.get(0).getCharacteristics();
        int[] chars2 = cards.get(1).getCharacteristics();
        int[] chars3 = cards.get(2).getCharacteristics();
        for (int i = 0; i < 4; i++) {
            if (chars1[i] == chars2[i]
                    && chars2[i] == chars3[i]) {
                continue;
            } else if (chars1[i] != chars2[i]
                    && chars2[i] != chars3[i]
                    && chars1[i] != chars3[i]) {
                continue;
            }
            match = false;
        }

        return match;
    }
}
