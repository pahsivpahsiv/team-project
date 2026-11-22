package use_case.set_check;

import entity.CardStuff;
import use_case.no_sets.NoSetsOutputBoundary;
import use_case.no_sets.SetCheckerStrategy;
import use_case.no_sets.SetsOutputData;

import java.util.ArrayList;

public class SetCheckInteractor implements SetCheckInputBoundary{

    private final SetCheckOutputBoundary setCheckPresenter;
    private final CardStuff cardStuff;
    private final SetCheckerStrategy setChecker;

    public SetCheckInteractor(SetCheckOutputBoundary setCheckPresenter, CardStuff cardStuff,
                              SetCheckerStrategy setChecker) {
        this.setCheckPresenter = setCheckPresenter;
        this.cardStuff = cardStuff;
        this.setChecker = setChecker;
    }

    @Override
    public void execute(ArrayList<Integer> inputData) {
        boolean result = setChecker.checkSet(inputData);
        if (result) {
            ArrayList<Integer> oldField = new ArrayList<>(inputData);
            ArrayList<Integer> newField = cardStuff.replaceCards(inputData);
            ArrayList<Integer> newCards = new ArrayList<>();
            for (int id : newField) {
                if (!oldField.contains(id)) {
                    newCards.add(id);
                }
            }
            SetsOutputData output = new SetsOutputData(newField, newCards);
            setCheckPresenter.prepareSuccessView(output);
        } else {
            setCheckPresenter.prepareFailView("This is not a set.");
        }
    }
}
