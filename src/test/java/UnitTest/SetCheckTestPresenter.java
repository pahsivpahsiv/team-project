package UnitTest;

import use_case.no_sets.SetsOutputData;
import use_case.set_check.SetCheckOutputBoundary;

public class SetCheckTestPresenter implements SetCheckOutputBoundary {

    @Override
    public void prepareSuccessView(SetsOutputData outputData) {
        System.out.println("success, this is a set, the cards are replaced.");
    }

    @Override
    public void prepareFailView(String message) {
        System.out.println("this is not a set");
    }
}
