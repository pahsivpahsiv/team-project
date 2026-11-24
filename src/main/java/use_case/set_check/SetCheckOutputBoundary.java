package use_case.set_check;

public interface SetCheckOutputBoundary {
    /**
     * Passes the output of the use case to the presenter.
     * @param outputData the output to pass on.
     */
    void prepareSuccessView(SetCheckOutputData outputData);

    /**
     * Passes the fail message of the use case to the presenter.
     * @param message the output to pass on.
     */
    void prepareFailView(String message);
}
