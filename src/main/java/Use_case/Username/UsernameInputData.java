package Use_case.Username;

public class UsernameInputData {

    private String username;

    /**
     * Creates a username according to the input.
     *
     * @param username the username of the player
     */

    public UsernameInputData(String username) {
        this.username = username;
    }


    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            this.username = "67";
            throw new IllegalArgumentException("Username can't be empty, your username is now 67 :P");
        }

        if (username.equals("67")) {
            this.username = username;
            System.out.println("You have a great taste in usernames <333");
        }
        if (username.length() == 67) {
            this.username = username;
            System.out.println("You have a great taste in username length <333");
        }

        if (username.length() < 3 || username.length() > 33) {
            this.username = "67";
            throw new IllegalArgumentException("Username length has to be between 3-33 characters" +
                    "(except 67)" + ", your username is now 67 :P");
        }
        this.username = username.trim();
    }
    public String getUsername() {
        return this.username;
    }
}