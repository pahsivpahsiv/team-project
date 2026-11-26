package use_case.set_username;

/**
 * Input Boundary for actions which are related to Setting up the username.
 */

public interface SetUsernameInputBoundary {
        /**
         * Executes the SetUsername use case.
         *
         * @param SetUsernameInputData the input data
         */
        void execute(SetUsernameInputData SetUsernameInputData);
    }
