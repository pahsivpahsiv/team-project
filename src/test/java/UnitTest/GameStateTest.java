
package UnitTest;

import entity.CardStuff;
import entity.GameState;

import entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState createGame() {
        CardStuff cards = new CardStuff();
        Player player = new Player("alice", 1);
        Player player1 = new Player("bob", 2);
        Player player2 = new Player("cleo", 3);

        return new GameState(
                cards, GameState.GameMode.SET, new Player[] {player, player1, player2});
    }

    private GameState createBadGame() {
        CardStuff cards = new CardStuff();
        Player player1 = new Player("alice", 1);
        Player player2 = new Player("alice", 1);
        return new GameState(
                cards, GameState.GameMode.SET, new Player[] {player1, player2});
    }

    @Test
    void testConstructor() {
        CardStuff cards = new CardStuff();
        Player player = new Player("alice", 1);
        GameState game = new GameState(
                cards, GameState.GameMode.SET, new Player[] {player});

        assertEquals(0, game.getTime());
        assertSame(cards, game.getCardStuff());
        assertEquals(GameState.GameStatus.PLAYING,
                game.getStatus());
        assertEquals(GameState.GameMode.SET,
                game.getMode());
    }

    @Test
    void testConctructorException() {
        assertThrows(IllegalArgumentException.class, () -> createBadGame());
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
    void testGetPlayer() {
        GameState game = createGame();
        assertEquals(1, game.getPlayer(1).getId());
    }

    @Test
    void testNullCardStuff() {
        Player player = new Player("alice", 1);
        assertThrows(NullPointerException.class,
                () -> new GameState(
                        null, GameState.GameMode.SET, new Player[] {player}));
    }

    @Test
    void testNullMode() {
        CardStuff cards = new CardStuff();
        Player player = new Player("alice", 1);
        assertThrows(NullPointerException.class,
                () -> new GameState(cards, null, new Player[] {player}));
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
