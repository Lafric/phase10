package Communication;

import Model.Player;
import org.junit.*;

import javafx.scene.control.TextArea;

import Model.Identity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.rmi.RemoteException;
import java.util.Optional;

public class LobbyTest {
    String lobbyName = "test";
    private Lobby lobby;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Before
    public void Setup() throws RemoteException {
        lobby = new LobbyImpl(lobbyName);
    }
    @Test
    public void ReturnLobbyName() throws RemoteException {
        assertEquals(lobby.getLobbyName(),"test");
    }
    @Test
    public void ReturnMaxPlayerCount() throws RemoteException {
        assertEquals(Optional.ofNullable(lobby.getMaxPlayerCount()),Optional.ofNullable(6));
    }
    @Test
    public void ReturnPlayerList() throws RemoteException {
        lobby.create_Playerlist();
        lobby.addBot();
        lobby.addBot();
        assertNotNull(lobby.getPlayerList());
    }
    @Test
    public void ReturnCurrentPlayerCount() throws RemoteException {
        lobby.create_Playerlist();
        lobby.addBot();
        lobby.addBot();
        assertEquals(Optional.ofNullable(2),Optional.ofNullable(lobby.getCurrentPlayerCount()));
    }
    @Test
    public void PlayerLeavesLobby() throws RemoteException {
        lobby.create_Playerlist();
        lobby.addBot();
        lobby.addBot();
        ArrayList<Identity> botList = lobby.getPlayerList();
        Identity bot = botList.get(0);
        lobby.leaveLobby(bot);
        assertEquals(Optional.ofNullable(lobby.getCurrentPlayerCount()),Optional.ofNullable(1));
    }
    @Test
    public void AddBotsUntilFull() throws RemoteException, InterruptedException {
        int i=0;
        while(i<7){
            lobby.addBot();
            Thread.sleep(1000);
            i++;
        }
        assertEquals("couldn't join lobby"+"\n", outContent.toString());

    }
}
