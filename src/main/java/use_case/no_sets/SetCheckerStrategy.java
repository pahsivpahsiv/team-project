package use_case.no_sets;

import java.util.ArrayList;

/**
 * Set Checker interface for algorithmic Set-related use cases.
 */
public interface SetCheckerStrategy {

    /**
     * Checks whether the input is a set.
     * @param ids the ids of cards to check.
     */
    boolean checkSet(ArrayList<Integer> ids);
}
