package use_case.no_sets;

import entity.CardStuff;

import java.util.ArrayList;

/**
 * The No Sets Interactor.
 */
public class NoSetsInteractor implements NoSetsInputBoundary {
    private final NoSetsOutputBoundary noSetsPresenter;
    private final CardStuff cardStuff;
    private final SetCheckerStrategy setChecker;

    public NoSetsInteractor (NoSetsOutputBoundary noSetsPresenter, CardStuff cardStuff, SetCheckerStrategy setChecker) {
        this.noSetsPresenter = noSetsPresenter;
        this.cardStuff = cardStuff;
        this.setChecker = setChecker;
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
                    boolean result = setChecker.checkSet(ids);

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
}
