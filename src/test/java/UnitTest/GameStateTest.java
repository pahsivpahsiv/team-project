
package UnitTest;

import entity.CardStuff;
import entity.GameState;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState createGame() {
        CardStuff cards = new CardStuff();

        return new GameState(
                cards, GameState.GameMode.SET);
    }

    @Test
    void testConstructor() {
        CardStuff cards = new CardStuff();

        GameState game = new GameState(
                cards, GameState.GameMode.SET);

        assertEquals(0, game.getTime());
        assertSame(cards, game.getCardStuff());
        assertEquals(GameState.GameStatus.PLAYING,
                game.getStatus());
        assertEquals(GameState.GameMode.SET,
                game.getMode());
    }

    @Test
    void testSetTime() {
        GameState game = createGame();

        game.setTime(120);

        assertEquals(120, game.getTime());
    }

    @Test
    void testNegativeTime() {
        GameState game = createGame();

        assertThrows(IllegalArgumentException.class,
                () -> game.setTime(-10));
    }

    @Test
    void testSetCardStuff() {
        GameState game = createGame();
        CardStuff newCards = new CardStuff();

        game.setCardStuff(newCards);

        assertSame(newCards, game.getCardStuff());
    }

    @Test
    void testPauseGame() {
        GameState game = createGame();

        game.setStatus(GameState.GameStatus.PAUSED);

        assertEquals(GameState.GameStatus.PAUSED,
                game.getStatus());
    }

    @Test
    void testResumeGame() {
        GameState game = createGame();

        game.setStatus(GameState.GameStatus.PAUSED);
        game.setStatus(GameState.GameStatus.PLAYING);

        assertEquals(GameState.GameStatus.PLAYING,
                game.getStatus());
    }

    @Test
    void testChangeMode() {
        GameState game = createGame();

        game.setMode(GameState.GameMode.ANTI_SET);

        assertEquals(GameState.GameMode.ANTI_SET,
                game.getMode());

        game.setMode(GameState.GameMode.GHOST_SET);

        assertEquals(GameState.GameMode.GHOST_SET,
                game.getMode());
    }

    @Test
    void testNullCardStuff() {
        assertThrows(NullPointerException.class,
                () -> new GameState(
                        null, GameState.GameMode.SET));
    }

    @Test
    void testNullMode() {
        CardStuff cards = new CardStuff();

        assertThrows(NullPointerException.class,
                () -> new GameState(cards, null));
    }

    @Test
    void testNullSetters() {
        GameState game = createGame();

        assertThrows(NullPointerException.class,
                () -> game.setCardStuff(null));

        assertThrows(NullPointerException.class,
                () -> game.setStatus(null));

        assertThrows(NullPointerException.class,
                () -> game.setMode(null));
    }
}
