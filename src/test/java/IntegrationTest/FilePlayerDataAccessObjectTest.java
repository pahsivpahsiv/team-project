package IntegrationTest;

import data_access.FilePlayerDataAccessObject;
import entity.Player;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FilePlayerDataAccessObjectTest {

    @Test
    void testSaveAndReloadPlayer() throws Exception {
        Path file = Files.createTempFile("players-test", ".txt");

        try {
            FilePlayerDataAccessObject dao =
                    new FilePlayerDataAccessObject(file.toString());

            Player player = new Player("67");
            player.updateBestTime(45000);
            dao.save(player);

            FilePlayerDataAccessObject reloadedDao =
                    new FilePlayerDataAccessObject(file.toString());

            assertTrue(reloadedDao.existsByName("67"));
            assertEquals(45000,
                    reloadedDao.get("67").getBestTime());
        }
        finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void testPlayerWithoutBestTime() throws Exception {
        Path file = Files.createTempFile("players-test", ".txt");

        try {
            FilePlayerDataAccessObject dao =
                    new FilePlayerDataAccessObject(file.toString());

            dao.save(new Player("Michael"));

            FilePlayerDataAccessObject reloadedDao =
                    new FilePlayerDataAccessObject(file.toString());

            assertTrue(reloadedDao.existsByName("Michael"));
            assertEquals(-1,
                    reloadedDao.get("Michael").getBestTime());
        }
        finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void testUpdatedBestTimeIsPersisted() throws Exception {
        Path file = Files.createTempFile("players-test", ".txt");

        try {
            FilePlayerDataAccessObject dao =
                    new FilePlayerDataAccessObject(file.toString());

            Player player = new Player("Romina");
            player.updateBestTime(50);
            dao.save(player);

            player.updateBestTime(30);
            dao.save(player);

            FilePlayerDataAccessObject reloadedDao =
                    new FilePlayerDataAccessObject(file.toString());

            assertEquals(30,
                    reloadedDao.get("Romina").getBestTime());
        }
        finally {
            Files.deleteIfExists(file);
        }
    }
}
