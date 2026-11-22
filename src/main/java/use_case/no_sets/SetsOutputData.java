package use_case.no_sets;

import java.util.ArrayList;

/**
 * An object for the return data of the set-checking related use cases.
 */
public class SetsOutputData {

    private final ArrayList<Integer> field;

    private final ArrayList<Integer> added;

    public SetsOutputData (ArrayList<Integer> field, ArrayList<Integer> added) {
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
