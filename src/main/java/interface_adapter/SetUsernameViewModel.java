
package interface_adapter;

import java.util.ArrayList;
import java.util.List;

public class SetUsernameViewModel {

    public static final String VIEW_NAME = "set username";

    private SetUsernameState state = new SetUsernameState();

    private final List<SetUsernameViewModelListener> listeners =
            new ArrayList<>();

    public SetUsernameState getState() {
        return state;
    }

    public void setState(SetUsernameState state) {
        this.state = state;
    }

    public void addListener(SetUsernameViewModelListener listener) {
        listeners.add(listener);
    }

    public void notifyStateChanged() {
        for (SetUsernameViewModelListener listener : listeners) {
            listener.stateChanged();
        }
    }
}
