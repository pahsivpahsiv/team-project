
package UnitTest;

import Use_case.SetUsername.SetUsernameInputData;
import Use_case.SetUsername.SetUsernameInteractor;
import Use_case.SetUsername.SetUsernameOutputBoundary;
import Use_case.SetUsername.SetUsernameOutputData;

import data_access.FilePlayerDataAccessObject;
import entity.Player;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SetUsernameInteractorTest {

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
    void testNewUsername() throws Exception {
        Path file = Files.createTempFile("players-test", ".txt");

        try {
            FilePlayerDataAccessObject dao =
                    new FilePlayerDataAccessObject(file.toString());

            TestPresenter presenter = new TestPresenter();
            SetUsernameInteractor interactor =
                    new SetUsernameInteractor(dao, presenter);

            interactor.execute(new SetUsernameInputData("Michelle"));

            assertTrue(presenter.success);
            assertTrue(dao.existsByName("Michelle"));
            assertEquals("Michelle", dao.getCurrentUsername());
        }
        finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void testExistingUsernameKeepsBestTime() throws Exception {
        Path file = Files.createTempFile("players-test", ".txt");

        try {
            FilePlayerDataAccessObject dao =
                    new FilePlayerDataAccessObject(file.toString());

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
        finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void testEmptyUsernameFails() throws Exception {
        Path file = Files.createTempFile("players-test", ".txt");

        try {
            FilePlayerDataAccessObject dao =
                    new FilePlayerDataAccessObject(file.toString());

            TestPresenter presenter = new TestPresenter();
            SetUsernameInteractor interactor =
                    new SetUsernameInteractor(dao, presenter);

            interactor.execute(new SetUsernameInputData(""));

            assertFalse(presenter.success);
            assertEquals("Username cannot be empty.",
                    presenter.errorMessage);
            assertNull(dao.getCurrentUsername());
        }
        finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void testWhitespaceUsernameFails() throws Exception {
        Path file = Files.createTempFile("players-test", ".txt");

        try {
            FilePlayerDataAccessObject dao =
                    new FilePlayerDataAccessObject(file.toString());

            TestPresenter presenter = new TestPresenter();
            SetUsernameInteractor interactor =
                    new SetUsernameInteractor(dao, presenter);

            interactor.execute(new SetUsernameInputData("   "));

            assertFalse(presenter.success);
            assertNull(dao.getCurrentUsername());
        }
        finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void testUsernameWithTabFails() throws Exception {
        Path file = Files.createTempFile("players-test", ".txt");

        try {
            FilePlayerDataAccessObject dao =
                    new FilePlayerDataAccessObject(file.toString());

            TestPresenter presenter = new TestPresenter();
            SetUsernameInteractor interactor =
                    new SetUsernameInteractor(dao, presenter);

            interactor.execute(new SetUsernameInputData("Michelle\tLi"));

            assertFalse(presenter.success);
            assertEquals("Username contains invalid characters.",
                    presenter.errorMessage);
            assertFalse(dao.existsByName("Michelle\tLi"));
        }
        finally {
            Files.deleteIfExists(file);
        }
    }

}
