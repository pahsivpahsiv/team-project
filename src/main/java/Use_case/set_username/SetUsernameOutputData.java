package use_case.set_username;
    /**
     * Output Data for the Username Use Case.
     */
    public class SetUsernameOutputData {

        private final String username;

        public SetUsernameOutputData(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

    }
