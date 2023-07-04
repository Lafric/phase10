package Model;
import java.io.Serializable;

public class Identity implements Serializable {
    private String username;

    public Identity(String name) {
        this.username = name;
    }


    public String getUsername() {
        return this.username;
    }
}
