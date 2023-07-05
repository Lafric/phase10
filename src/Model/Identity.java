package Model;
import java.io.Serializable;

public class Identity implements Serializable {
    private String username;

    private String currentLobby; 


    public Identity(String name) {
        this.username = name;
    }

    public void setCurrentLobby(String lobbyName){
        this.currentLobby = lobbyName; 
    }
    public String getCurrentLobby(){
        return this.currentLobby; 
    }   

    public String getUsername() {
        return this.username;
    }
}
