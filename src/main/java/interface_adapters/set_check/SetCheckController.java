package interface_adapters.set_check;

import use_case.no_sets.NoSetsInputBoundary;
import use_case.set_check.SetCheckInputBoundary;
import use_case.set_check.SetCheckInputData;

import java.util.ArrayList;

/**
 * The controller for the Set Check Use Case.
 */
public class SetCheckController {

    private final SetCheckInputBoundary setCheckInteractor;

    public SetCheckController(SetCheckInputBoundary setCheckInteractor) {
        this.setCheckInteractor = setCheckInteractor;
    }

    /**
     * Executes the No Sets Use Case.
     * @param cards chosen cards to check.
     */
    public void execute(SetCheckInputData cards) {
        setCheckInteractor.execute(cards);
    }
}
