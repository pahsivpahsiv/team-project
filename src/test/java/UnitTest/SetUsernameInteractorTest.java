
package UnitTest;

import use_case.SetUsername.SetUsernameInputData;
import use_case.SetUsername.SetUsernameInteractor;
import use_case.SetUsername.SetUsernameOutputBoundary;
import use_case.SetUsername.SetUsernameOutputData;
import use_case.SetUsername.SetUsernameUserDataAccessInterface;
import entity.Player;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SetUsernameInteractorTest {

    // A fake DAO that stores players in memory instead of a TXT file.
    private static class FakePlayerDAO
            implements SetUsernameUserDataAccessInterface {

        private final Map<String, Player> players = new HashMap<>();
        private String currentUsername;

        @Override
        public int getNextPlayerId() {
            int nextId = 0;

            for (Player player : players.values()) {
                nextId = Math.max(nextId, player.getId() + 1);
            }

            return nextId;
        }

        @Override
        public boolean existsByName(String username) {
            return players.containsKey(username);
        }

        @Override
        public void save(Player player) {
            players.put(player.getUsername(), player);
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

    // Records whether the interactor reports success or failure.
    private static class TestPresenter
            implements SetUsernameOutputBoundary {

        boolean success;
        String errorMessage;

        @Override
        public void prepareSuccessView(SetUsernameOutputData outputData) {
            success = true;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    @Test
    void testNewUsername() {
        FakePlayerDAO dao = new FakePlayerDAO();
        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        interactor.execute(new SetUsernameInputData("Michelle"));

        assertTrue(presenter.success);
        assertTrue(dao.existsByName("Michelle"));
        assertEquals("Michelle", dao.getCurrentUsername());
    }

    @Test
    void testExistingUsernameKeepsBestTime() {
        FakePlayerDAO dao = new FakePlayerDAO();

        Player player = new Player("Thea", 0);
        player.updateBestTime(30);
        dao.save(player);

        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        interactor.execute(new SetUsernameInputData("Thea"));

        assertTrue(presenter.success);
        assertEquals(30, dao.get("Thea").getBestTime());
    }

    @Test
    void testEmptyUsernameFails() {
        FakePlayerDAO dao = new FakePlayerDAO();
        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        interactor.execute(new SetUsernameInputData(""));

        assertFalse(presenter.success);
        assertEquals("Username cannot be empty.",
                presenter.errorMessage);
        assertNull(dao.getCurrentUsername());
    }

    @Test
    void testWhitespaceUsernameFails() {
        FakePlayerDAO dao = new FakePlayerDAO();
        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        interactor.execute(new SetUsernameInputData("   "));

        assertFalse(presenter.success);
        assertNull(dao.getCurrentUsername());
    }

    @Test
    void testUsernameWithTabFails() {
        FakePlayerDAO dao = new FakePlayerDAO();
        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        interactor.execute(new SetUsernameInputData("Nai\tWa"));

        assertFalse(presenter.success);
        assertEquals("Username contains invalid characters.",
                presenter.errorMessage);
        assertFalse(dao.existsByName("Nai\tWa"));
    }

    @Test
    void testUsernameTooShortFails() {
        FakePlayerDAO dao = new FakePlayerDAO();
        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        interactor.execute(new SetUsernameInputData("AB"));

        assertFalse(presenter.success);
        assertEquals("Username must be 3-33 characters long (except 67).",
                presenter.errorMessage);
        assertFalse(dao.existsByName("AB"));
    }

    @Test
    void testUsernameTooLongFails() {
        FakePlayerDAO dao = new FakePlayerDAO();
        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        String username = "A".repeat(34);
        interactor.execute(new SetUsernameInputData(username));

        assertFalse(presenter.success);
        assertFalse(dao.existsByName(username));
    }

    @Test
    void testUsername67IsAllowed() {
        FakePlayerDAO dao = new FakePlayerDAO();
        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        interactor.execute(new SetUsernameInputData("67"));

        assertTrue(presenter.success);
        assertTrue(dao.existsByName("67"));
    }

    @Test
    void testUsernameWith67CharactersIsAllowed() {
        FakePlayerDAO dao = new FakePlayerDAO();
        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        String username = "A".repeat(67);
        interactor.execute(new SetUsernameInputData(username));

        assertTrue(presenter.success);
        assertTrue(dao.existsByName(username));
    }

}