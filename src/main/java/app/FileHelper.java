package app;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {
    public static Path getAppDataDir(){
        return Paths.get(System.getProperty("user.home"), ".setgame");
    }

    public static Path getUploadDir(){
        return getAppDataDir().resolve("uploads");
    }
}
