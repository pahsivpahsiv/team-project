package use_case.SetUsername;
import entity.Player;

/**
 * Implements the Set Username use case.
 * Retrieves an existing player or creates a new player
 * after validating the username.
 */
public class SetUsernameInteractor implements SetUsernameInputBoundary {

    private final SetUsernameUserDataAccessInterface userDataAccessObject;
    private final SetUsernameOutputBoundary setUsernamePresenter;

    /**
     * Creates an interactor with the required dependencies.
     *
     * @param userDataAccessInterface the interface for accessing player data
     * @param loginOutputBoundary the output boundary for presenting results
     */
    public SetUsernameInteractor(SetUsernameUserDataAccessInterface userDataAccessInterface,
                           SetUsernameOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.setUsernamePresenter = loginOutputBoundary;
    }

    /**
     * Executes the Set Username use case.
     * Validates the username, retrieves or creates the player,
     * and prepares the corresponding success or failure view.
     *
     * @param loginInputData the username input data
     */
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

        if (!username.equals("67")
                && username.length() != 67
                && (username.length() < 3 || username.length() > 33)) {
            setUsernamePresenter.prepareFailView(
                    "Username must be 3-33 characters long (except 67).");
            return;
        }


        final Player player;

        if (userDataAccessObject.existsByName(username)) {
            player = userDataAccessObject.get(username);
        }
        else {
            player = new Player(username, userDataAccessObject.getNextPlayerId());
            userDataAccessObject.save(player);
        }

        userDataAccessObject.setCurrentUsername(username);

        final SetUsernameOutputData outputData =
                new SetUsernameOutputData(player.getUsername());

        setUsernamePresenter.prepareSuccessView(outputData);
    }
}