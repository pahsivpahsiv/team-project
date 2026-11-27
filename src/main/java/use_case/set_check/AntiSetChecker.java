package use_case.set_check;

import entity.Card;
import entity.CardStuff;
import use_case.no_sets.SetCheckerStrategy;

import java.util.ArrayList;
import java.util.Arrays;

public class AntiSetChecker implements SetCheckerStrategy {

    private final CardStuff cardStuff;

    public AntiSetChecker(CardStuff cardStuff) {
        this.cardStuff = cardStuff;
    }

    @Override
    public boolean checkSet(ArrayList<Integer> ids) {
        boolean match = false;
        int[][][] combinations = new int[3][2][2];
        combinations[0] = new int[][]{{0, 1}, {2, 3}};
        combinations[1] = new int[][]{{0, 2}, {1, 3}};
        combinations[2] = new int[][]{{0, 3}, {1, 2}};

        for (int[][] comb : combinations) {
            int[] charsFirst = {};
            int[] charsSecond = {};
            for (int[] pair : comb) {
                ArrayList<Integer> pairIds = new ArrayList<Integer>();
                pairIds.add(ids.get(pair[0]));
                pairIds.add(ids.get(pair[1]));
                ArrayList<Card> cards = cardStuff.getCards(pairIds);
                int[] chars1 = cards.get(0).getCharacteristics();
                int[] chars2 = cards.get(1).getCharacteristics();
                int[] chars3 = new int[4];
                for (int i = 0; i < 4; i++) {
                    if (chars1[i] == chars2[i]) {
                        chars3[i] = chars1[i];
                    } else {
                        for (int j = 1; j < 4; j++){
                            if (chars1[i] != j && chars2[i] != j) {
                                chars3[i] = j;
                                break;
                            }
                        }
                    }
                }
                if (charsFirst.length == 0) {
                    charsFirst = chars3;
                } else {
                    charsSecond = chars3;
                }
            }
            if (Arrays.equals(charsFirst, charsSecond)) {
                match = true;
                break;
            }
        }
        return match;
    }
}
