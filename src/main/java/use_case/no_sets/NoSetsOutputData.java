package use_case.no_sets;

import java.util.ArrayList;

/**
 * An object for the return data of the No Sets use case.
 */
public class NoSetsOutputData {

    private final ArrayList<Integer> field;

    private final ArrayList<Integer> added;

    public NoSetsOutputData (ArrayList<Integer> field, ArrayList<Integer> added) {
        this.field = field;
        this.added = added;
    }

    public ArrayList<Integer> getField() {
        return field;
    }

    public ArrayList<Integer> getAdded() {
        return added;
    }
}
