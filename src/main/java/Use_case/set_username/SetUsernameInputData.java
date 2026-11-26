package use_case.set_username;

public class SetUsernameInputData {

    private String username;

    /**
     * Creates a username according to the input.
     *
     * @param username the username of the player
     */

    public SetUsernameInputData(String username) {
        setUsername(username);
    }


    public void setUsername(String username) {
        this.username = username;

        if (username == null) {
            return;
        }

        if (username.equals("67")) {
            System.out.println("You have a great taste in usernames <333");
        }

        if (username.length() == 67) {
            System.out.println("You have a great taste in username length <333");
        }
    }

    public String getUsername() {
        return username;
    }
}