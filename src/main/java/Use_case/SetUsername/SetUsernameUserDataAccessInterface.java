package Use_case.SetUsername;
import entity.Player;

public interface SetUsernameUserDataAccessInterface {
    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(Player player);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    Player get(String username);

    void setCurrentUsername(String name);

    String getCurrentUsername();
}
