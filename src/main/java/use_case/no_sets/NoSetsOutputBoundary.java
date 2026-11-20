package use_case.no_sets;

public interface NoSetsOutputBoundary {

    void prepareSuccessView(NoSetsOutputData outputData);

    void prepareFailView(String message);
}
