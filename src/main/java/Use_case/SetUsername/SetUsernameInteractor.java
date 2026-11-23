package Use_case.SetUsername;
import entity.Player;
public class SetUsernameInteractor {

    private final SetUsernameUserDataAccessInterface userDataAccessObject;
    private final SetUsernameOutputBoundary SetUsernamePresenter;

    public SetUsernameInteractor(SetUsernameUserDataAccessInterface userDataAccessInterface,
                           SetUsernameOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.SetUsernamePresenter = loginOutputBoundary;
    }

    @Override
    public void execute(SetUsernameInputData loginInputData) {
        final String username = loginInputData.getUsername();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
            else {

                final Player player = userDataAccessObject.get(SetUsernameInputData.getUsername());

                userDataAccessObject.setCurrentUsername(username);

                final SetUsernameOutputData loginOutputData = new SetUsernameOutputData(player.getName());
                SetUsernamePresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
