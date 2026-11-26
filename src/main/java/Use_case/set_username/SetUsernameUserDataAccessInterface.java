package use_case.set_username;
import entity.Player;

public interface SetUsernameUserDataAccessInterface {
    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the player.
     * @param player the user to save
     */
    void save(Player player);

    /**
     * Returns the player with the given username.
     * @param username the username to look up
     * @return the player with the given username
     */
    Player get(String username);

    void setCurrentUsername(String name);

    String getCurrentUsername();

    /**
     * Returns an available ID for a new player.
     *
     * @return an available player ID
     */
    int getNextPlayerId();
}