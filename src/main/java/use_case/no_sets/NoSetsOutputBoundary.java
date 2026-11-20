package use_case.no_sets;

import java.util.ArrayList;

public interface NoSetsOutputBoundary {

    void prepareSuccessView(NoSetsOutputData outputData);

    void prepareFailView(String message);
}
