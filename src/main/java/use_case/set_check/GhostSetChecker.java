package use_case.set_check;

import entity.Card;
import entity.CardStuff;
import use_case.no_sets.SetCheckerStrategy;

import java.util.ArrayList;

public class GhostSetChecker implements SetCheckerStrategy {

    private final CardStuff cardStuff;

    public GhostSetChecker(CardStuff cardStuff) {
        this.cardStuff = cardStuff;
    }

    @Override
    public boolean checkSet(ArrayList<Integer> ids) {
        boolean match = false;

        int[][][] combinations = new int[15][3][2];
        combinations[0] = new int[][]{{0, 1}, {2, 3}, {4, 5}};
        combinations[1] = new int[][]{{0, 2}, {1, 3}, {4, 5}};
        combinations[2] = new int[][]{{0, 3}, {1, 2}, {4, 5}};

        combinations[3] = new int[][]{{0, 4}, {2, 3}, {1, 5}};
        combinations[4] = new int[][]{{0, 2}, {4, 3}, {1, 5}};
        combinations[5] = new int[][]{{0, 3}, {4, 2}, {1, 5}};

        combinations[6] = new int[][]{{0, 5}, {2, 3}, {1, 4}};
        combinations[7] = new int[][]{{0, 2}, {5, 3}, {1, 4}};
        combinations[8] = new int[][]{{0, 3}, {5, 2}, {1, 4}};

        combinations[9] = new int[][]{{0, 5}, {2, 1}, {3, 4}};
        combinations[10] = new int[][]{{0, 2}, {5, 1}, {3, 4}};
        combinations[11] = new int[][]{{0, 1}, {5, 2}, {3, 4}};

        combinations[12] = new int[][]{{3, 5}, {2, 1}, {0, 4}};
        combinations[13] = new int[][]{{3, 2}, {5, 1}, {0, 4}};
        combinations[14] = new int[][]{{3, 1}, {5, 2}, {0, 4}};

        for (int[][] comb : combinations) {
            int[] charsFirst = {};
            int[] charsSecond = {};
            int[] charsThird = {};
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
                        for (int j = 1; j < 4; j++) {
                            if (chars1[i] != j && chars2[i] != j) {
                                chars3[i] = j;
                                break;
                            }
                        }
                    }
                }
                if (charsFirst.length == 0) {
                    charsFirst = chars3;
                } else if (charsSecond.length == 0) {
                    charsSecond = chars3;
                } else {
                    charsThird = chars3;
                }
            }

            boolean currentMatch = true;
            for (int i = 0; i < 4; i++) {
                if (charsFirst[i] == charsSecond[i]
                        && charsSecond[i] == charsThird[i]) {
                    continue;
                } else if (charsFirst[i] != charsSecond[i]
                        && charsSecond[i] != charsThird[i]
                        && charsFirst[i] != charsThird[i]) {
                    continue;
                }
                currentMatch = false;
            }
            if (currentMatch) {
                match = true;
                break;
            }
        }
        return match;
    }
}
