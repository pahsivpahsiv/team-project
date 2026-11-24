package use_case.set_check;

import java.util.ArrayList;

public class SetCheckOutputData {
    private final ArrayList<Integer> field;

    private final ArrayList<Integer> added;

    private final int score;

    private final String username;

    public SetCheckOutputData(ArrayList<Integer> field, ArrayList<Integer> added, int score, String username) {
        this.field = field;
        this.added = added;
        this.score = score;
        this.username = username;
    }

    public String getUsername() {
        return username;
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
}
