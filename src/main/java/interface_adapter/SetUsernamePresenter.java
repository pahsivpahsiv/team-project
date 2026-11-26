
package interface_adapter;

import use_case.set_username.SetUsernameOutputBoundary;
import use_case.set_username.SetUsernameOutputData;

public class SetUsernamePresenter implements SetUsernameOutputBoundary {

    private final SetUsernameViewModel viewModel;

    public SetUsernamePresenter(SetUsernameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SetUsernameOutputData outputData) {
        final SetUsernameState state = viewModel.getState();

        state.setUsername(outputData.getUsername());
        state.setUsernameError(null);

        viewModel.notifyStateChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SetUsernameState state = viewModel.getState();

        state.setUsernameError(errorMessage);

        viewModel.notifyStateChanged();
    }
}
