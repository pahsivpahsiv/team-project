package UnitTest;

import entity.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testConstructor() {
        Player player = new Player("Romina");

        assertEquals("Romina", player.getUsername());
        assertEquals(0, player.getScore());
        assertEquals(-1, player.getBestTime());
    }
    @Test
    void testSetUsername() {
        Player player = new Player("Romina");

        player.setUsername("Alex");

        assertEquals("Alex", player.getUsername());
    }

    @Test
    void testIncrementScore() {
        Player player = new Player("Romina");

        player.incrementScore();
        player.incrementScore();

        assertEquals(2, player.getScore());
    }

    @Test
    void testResetScore() {
        Player player = new Player("Romina");

        player.incrementScore();
        player.resetScore();

        assertEquals(0, player.getScore());
    }

    @Test
    void testUpdateBestTime() {
        Player player = new Player("Romina");

        player.updateBestTime(60000);
        assertEquals(60000, player.getBestTime());

        player.updateBestTime(45000);
        assertEquals(45000, player.getBestTime());

        player.updateBestTime(90000);
        assertEquals(45000, player.getBestTime());
    }

    @Test
    void testInvalidUsername() {
        assertThrows(IllegalArgumentException.class,
                () -> new Player(""));
    }

    @Test
    void testInvalidBestTime() {
        Player player = new Player("Romina");

        assertThrows(IllegalArgumentException.class,
                () -> player.updateBestTime(-100));
    }
}