
package data_access;

import Use_case.SetUsername.SetUsernameUserDataAccessInterface;
import entity.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FilePlayerDataAccessObject
        implements SetUsernameUserDataAccessInterface {

    private final File txtFile;
    private final Map<String, Player> players = new HashMap<>();
    private String currentUsername;

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

    @Override
    public boolean existsByName(String username) {
        return players.containsKey(username);
    }

    @Override
    public void save(Player player) {
        players.put(player.getUsername(), player);
        saveToFile();
    }

    @Override
    public Player get(String username) {
        return players.get(username);
    }

    @Override
    public void setCurrentUsername(String username) {
        currentUsername = username;
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }
}
