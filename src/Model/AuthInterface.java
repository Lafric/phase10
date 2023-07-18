package Model;

import Model.AuthResult;

/**
 * Interface for Authentication Result
 */
public interface AuthInterface {
    /**
     * Method to perform authentication
     * @param name player name
     * @param pw player password
     * @return authentication object
     */
    AuthResult execute(String name, String pw);
}

