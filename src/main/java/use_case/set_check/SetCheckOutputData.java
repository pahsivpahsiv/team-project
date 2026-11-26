package use_case.set_check;

import java.util.ArrayList;

/**
 * The output of the Set Check use case.
 */
public class SetCheckOutputData {

    /**
     * New state of the field after the cards have been replaced.
     */
    private final ArrayList<Integer> field;

    /**
     * The new cards on the field.
     */
    private final ArrayList<Integer> added;

    /**
     * The new score of the player.
     */
    private final int score;

    /**
     * The player's id.
     */
    private final int id;

    public SetCheckOutputData(ArrayList<Integer> field, ArrayList<Integer> added,
                              int score, int id) {
        this.field = field;
        this.added = added;
        this.score = score;
        this.id = id;
    }

    public ArrayList<Integer> getAdded() {
        return added;
    }

    public ArrayList<Integer> getField() {
        return field;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return this.id;
    }
}
