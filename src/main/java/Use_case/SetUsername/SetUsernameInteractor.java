package Use_case.SetUsername;
import entity.Player;

public class SetUsernameInteractor implements SetUsernameInputBoundary {

    private final SetUsernameUserDataAccessInterface userDataAccessObject;
    private final SetUsernameOutputBoundary setUsernamePresenter;

    public SetUsernameInteractor(SetUsernameUserDataAccessInterface userDataAccessInterface,
                           SetUsernameOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.setUsernamePresenter = loginOutputBoundary;
    }

    @Override
    public void execute(SetUsernameInputData loginInputData) {

        final String username = loginInputData.getUsername();

        if (username == null || username.trim().isEmpty()) {
            setUsernamePresenter.prepareFailView("Username cannot be empty.");
            return;
        }

        if (username.contains("\t")
                || username.contains("\n")
                || username.contains("\r")) {
            setUsernamePresenter.prepareFailView(
                    "Username contains invalid characters.");
            return;
        }

        final Player player;

        if (userDataAccessObject.existsByName(username)) {
            player = userDataAccessObject.get(username);
        }
        else {
            player = new Player(username);
            userDataAccessObject.save(player);
        }

        userDataAccessObject.setCurrentUsername(username);

        final SetUsernameOutputData outputData =
                new SetUsernameOutputData(player.getUsername());

        setUsernamePresenter.prepareSuccessView(outputData);
    }
}