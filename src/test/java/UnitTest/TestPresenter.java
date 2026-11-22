package UnitTest;
import use_case.no_sets.NoSetsOutputBoundary;
import use_case.no_sets.SetsOutputData;

public class TestPresenter implements NoSetsOutputBoundary {
    @Override
    public void prepareSuccessView(SetsOutputData outputData) {
        System.out.println("success, there are no sets.");
    }

    @Override
    public void prepareFailView(String message) {
        System.out.println("there are sets.");
    }
}
