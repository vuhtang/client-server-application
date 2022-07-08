package transferring;

import java.io.Serializable;

/**
 * Entity to store user data, passed along with the request.
 */
public class Token implements Serializable {
    private final String userName;
    private final String userPassword;

    public Token(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
