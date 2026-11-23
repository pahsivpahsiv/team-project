package interface_adapter;

import Use_case.SetUsername.SetUsernameInputBoundary;
import Use_case.SetUsername.SetUsernameInputData;

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
