package interface_adapters.no_sets;

import use_case.no_sets.NoSetsInputBoundary;

import java.util.ArrayList;

/**
 * The controller for the No Sets Use Case.
 */
public class NoSetsController {

    private final NoSetsInputBoundary noSetsInteractor;

    public NoSetsController(NoSetsInputBoundary noSetsInteractor) {
        this.noSetsInteractor = noSetsInteractor;
    }

    /**
     * Executes the No Sets Use Case.
     * @param field the current state of the field.
     */
    public void execute(ArrayList<Integer> field) {
        noSetsInteractor.execute(field);
    }
}
