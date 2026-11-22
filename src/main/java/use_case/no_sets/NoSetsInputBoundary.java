package use_case.no_sets;

import java.util.ArrayList;

/**
 * No Sets use case input boundary.
 */
public interface NoSetsInputBoundary {

    /**
     * Execute the No Sets Use Case.
     * @param inputData the input data for this use case
     */
    void execute(ArrayList<Integer> inputData);
}
