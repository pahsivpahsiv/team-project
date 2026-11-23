
package interface_adapters.pause_game;

import java.util.ArrayList;
import java.util.List;

/**
 * View model for the Pause Game use case.
 */
public class PauseGameViewModel {

    public static final String VIEW_NAME = "pause game";

    private PauseGameState state = new PauseGameState();

    private final List<PauseGameViewModelListener> listeners =
            new ArrayList<>();

    public PauseGameState getState() {
        return state;
    }

    public void setState(PauseGameState state) {
        this.state = state;
    }

    public void addListener(PauseGameViewModelListener listener) {
        listeners.add(listener);
    }

    public void notifyStateChanged() {
        for (PauseGameViewModelListener listener : listeners) {
            listener.stateChanged();
        }
    }
}
