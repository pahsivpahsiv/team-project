/*

package data_access;

import Use_case.SetUsername.SetUsernameUserDataAccessInterface;
import entity.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * A file-based data access object for storing player information.
 * Player usernames and best times are saved in a tab-separated TXT file.
 *
 * <p>Each line contains a username and its best time.
 * A best time of -1 indicates that the player has no recorded time.
 *
 * <p>The current username is stored in memory only and is not
 * persisted to the file.
 *
 * <p>Invalid or corrupted file data may cause an exception.
 *//*

public class FilePlayerDataAccessObject
        implements SetUsernameUserDataAccessInterface {

    private final File txtFile;
    private final Map<String, Player> players = new HashMap<>();
    private String currentUsername;

    */
/**
     * Creates a file-based player data access object.
     * Loads existing players from the TXT file, or creates
     * an empty file if it does not exist.
     *
     * @param filePath the path to the player data file
     * @throws RuntimeException if the file cannot be read or created
     *//*

    public FilePlayerDataAccessObject(String filePath) {
        txtFile = new File(filePath);

        if (txtFile.exists()) {
            try (BufferedReader reader =
                         new BufferedReader(new FileReader(txtFile))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    if (!line.isBlank()) {
                        String[] data = line.split("\t", -1);

                        if (data.length != 2) {
                            throw new IllegalArgumentException(
                                    "Invalid player record: " + line);
                        }

                        Player player = new Player(data[0]);
                        long bestTime = Long.parseLong(data[1]);

                        if (bestTime != -1) {
                            player.updateBestTime(bestTime);
                        }

                        players.put(player.getUsername(), player);
                    }
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            saveToFile();
        }
    }

    */
/**
     * Writes all players to the TXT file.
     * Each line contains a username and best time,
     * separated by a tab character.
     *
     * @throws RuntimeException if writing to the file fails
     *//*

    private void saveToFile() {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(txtFile))) {

            for (Player player : players.values()) {
                writer.write(player.getUsername()
                        + "\t" + player.getBestTime());
                writer.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    */
/**
     * Checks whether a player with the given username exists.
     *
     * @param username the username to search for
     * @return true if the player exists, false otherwise
     *//*

    @Override
    public boolean existsByName(String username) {
        return players.containsKey(username);
    }

    @Override
    public void save(Player player) {
        players.put(player.getUsername(), player);
        saveToFile();
    }

    */
/**
     * Retrieves a player by username.
     *
     * @param username the username of the player
     * @return the matching player, or null if not found
     *//*

    @Override
    public Player get(String username) {
        return players.get(username);
    }

    */
/**
     * Sets the username of the currently selected player.
     * This value is stored in memory and is not persisted
     * to the TXT file.
     *
     * @param username the current player's username
     *//*

    @Override
    public void setCurrentUsername(String username) {
        currentUsername = username;
    }

    */
/**
     * Returns the username of the currently selected player.
     *
     * @return the current username, or null if none is selected
     *//*

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }
}
*/
