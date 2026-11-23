
package UnitTest;

import Use_case.SetUsername.SetUsernameInputData;
import Use_case.SetUsername.SetUsernameInteractor;
import Use_case.SetUsername.SetUsernameOutputBoundary;
import Use_case.SetUsername.SetUsernameOutputData;
import Use_case.SetUsername.SetUsernameUserDataAccessInterface;
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

        Player player = new Player("Michelle");
        player.updateBestTime(30);
        dao.save(player);

        TestPresenter presenter = new TestPresenter();
        SetUsernameInteractor interactor =
                new SetUsernameInteractor(dao, presenter);

        interactor.execute(new SetUsernameInputData("Michelle"));

        assertTrue(presenter.success);
        assertEquals(30, dao.get("Michelle").getBestTime());
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

        interactor.execute(new SetUsernameInputData("Michelle\tLi"));

        assertFalse(presenter.success);
        assertEquals("Username contains invalid characters.",
                presenter.errorMessage);
        assertFalse(dao.existsByName("Michelle\tLi"));
    }
}