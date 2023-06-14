package Model;

public class AuthResult {
    public boolean success;
    public String error;

    public AuthResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
