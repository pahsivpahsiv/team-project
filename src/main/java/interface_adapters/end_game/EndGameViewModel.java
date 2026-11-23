package interface_adapters.end_game;

import java.util.ArrayList;
import java.util.List;

public class EndGameViewModel {

    public static final String VIEW_NAME = "end game";

    private EndGameState state = new EndGameState();

    private final List<EndGameViewModelListener> listeners = new ArrayList<>();

    public EndGameState getState() {
        return state;
    }

    public void setState(EndGameState state) {
        this.state = state;
    }

    public void addListener(EndGameViewModelListener listener) {
        listeners.add(listener);
    }

    public void notifyStateChanged() {
        for (EndGameViewModelListener listener : listeners) {
            listener.stateChanged();
        }
    }
}
