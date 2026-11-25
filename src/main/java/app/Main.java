package app;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    /**
     * 🐐
     * @param args
     */
    public static void main(String[] args) {
        // Sets up the app data directories (for image/file IO uploads and such)
        Path appDataDir = Paths.get(System.getProperty("user.home"), ".setgame");
        Path uploadsDir = appDataDir.resolve("uploads");
        try {
            Files.createDirectories(uploadsDir);
        } catch (IOException e) {
            System.out.println("Something went wrong when creating the file directory. " +
                    "Falling back to default resources.");
            // throw new RuntimeException(e); <-- TODO - litigate this better
        }

        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .setupMainPanel()
                .addScreenRegions()
                .addControlFlow()
                .addTestCard()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
