package UnitTest;
import use_case.no_sets.NoSetsOutputBoundary;
import use_case.no_sets.NoSetsOutputData;

public class TestPresenter implements NoSetsOutputBoundary {
    @Override
    public void prepareSuccessView(NoSetsOutputData outputData) {
        System.out.println("success, there are no sets.");
    }

    @Override
    public void prepareFailView(String message) {
        System.out.println("there are sets.");
    }
}
