package use_case.no_sets;

/**
 * Output boundary interface for NoSets use case.
 */
public interface NoSetsOutputBoundary {
    /**
     * Passes the output of the use case to the presenter.
     * @param outputData the output to pass on.
     */
    void prepareSuccessView(NoSetsOutputData outputData);

    /**
     * Passes the fail message of the use case to the presenter.
     * @param message the output to pass on.
     */
    void prepareFailView(String message);
}
