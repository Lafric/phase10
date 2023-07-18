package Communication;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerFuncsTest {

    public ServerFuncs server;

    @Before
    public void Setup() throws RemoteException {
        server = new ServerFuncsImpl();
    }
    @Test
    public void ReturnLobbyCount() throws RemoteException{
        server.createLobby();
        ArrayList<Lobby> actualLobbies = server.getLobbyList();
        assertEquals(actualLobbies.size(),1);
    }
}
