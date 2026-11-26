package interface_adapter;

import use_case.SetUsername.SetUsernameInputBoundary;
import use_case.SetUsername.SetUsernameInputData;

    public class SetUsernameController {

        private final SetUsernameInputBoundary setUsernameInteractor;

        public SetUsernameController(SetUsernameInputBoundary setUsernameInteractor) {
            this.setUsernameInteractor = setUsernameInteractor;
        }

        public void execute(String username) {
            final SetUsernameInputData inputData =
                    new SetUsernameInputData(username);

            setUsernameInteractor.execute(inputData);
        }
    }
