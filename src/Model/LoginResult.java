package Model;

public class LoginResult {
    public boolean success;
    public String error;
    public Identity identity;

    public LoginResult(boolean success, String error, Identity identity) {
        this.success = success;
        this.error = error;
        this.identity = identity;
    }
}
