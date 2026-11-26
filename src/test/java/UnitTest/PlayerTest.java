package UnitTest;

import entity.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testConstructor() {
        Player player = new Player("Romina", 1);

        assertEquals("Romina", player.getUsername());
        assertEquals(0, player.getScore());
        assertEquals(-1, player.getBestTime());
        assertEquals(1, player.getId());
    }
    @Test
    void testSetUsername() {
        Player player = new Player("Romina", 1);

        player.setUsername("Alex");

        assertEquals("Alex", player.getUsername());
    }

    @Test
    void testIncrementScore() {
        Player player = new Player("Romina", 1);

        player.incrementScore();
        player.incrementScore();

        assertEquals(2, player.getScore());
    }

    @Test
    void testResetScore() {
        Player player = new Player("Romina", 1);

        player.incrementScore();
        player.resetScore();

        assertEquals(0, player.getScore());
    }

    @Test
    void testUpdateBestTime() {
        Player player = new Player("Romina", 1);

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
                () -> new Player("", 1));
    }

    @Test
    void testInvalidBestTime() {
        Player player = new Player("Romina", 1);

        assertThrows(IllegalArgumentException.class,
                () -> player.updateBestTime(-100));
    }
}