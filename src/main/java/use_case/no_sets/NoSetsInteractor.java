package use_case.no_sets;

import entity.Card;
import entity.CardStuff;

import java.util.ArrayList;

/**
 * The No Sets Interactor.
 */
public class NoSetsInteractor implements NoSetsInputBoundary, SetChecker {
    private final NoSetsOutputBoundary noSetsPresenter;
    private final CardStuff cardStuff;

    public NoSetsInteractor (NoSetsOutputBoundary noSetsPresenter, CardStuff cardStuff) {
        this.noSetsPresenter = noSetsPresenter;
        this.cardStuff = cardStuff;
    }

    @Override
    public void execute(ArrayList<Integer> inputData) {
        boolean flag = true;
        outerloop:
        for (int i : inputData) {
            for (int j : inputData) {
                if (i == j) {
                    continue;
                }
                for (int k : inputData) {
                    if (i == k || j == k) {
                        continue;
                    }
                    ArrayList<Integer> ids = new ArrayList<>();
                    ids.add(i);
                    ids.add(j);
                    ids.add(k);
                    boolean result = this.checkSet(ids);

                    if (!result) {
                        noSetsPresenter.prepareFailView("There is a set on the field.");
                        flag = false;
                        break outerloop;
                    }
                }
            }
        }
        if (flag) {
            ArrayList<Integer> removeField = new ArrayList<>(inputData);
            ArrayList<Integer> newField = cardStuff.expandField();
            ArrayList<Integer> newCards = new ArrayList<>(newField);
            newCards.removeAll(removeField);
            NoSetsOutputData outputData = new NoSetsOutputData(newField, newCards);

            noSetsPresenter.prepareSuccessView(outputData);
        }
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
