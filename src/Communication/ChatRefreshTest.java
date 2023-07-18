package Communication;
import javafx.scene.control.TextArea;

import Model.Identity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class ChatRefreshTest {
    @InjectMocks
    ServerFuncs mockServerFuncs;
    @Mock
    private Registry mockRegistryFuncs;
    @Mock
    private TextArea mockChatAusgabe;
    @Mock
    private MessageService messageService;
    @Mock
    private ArrayList<Message> mockMessages;
    @Mock
    private Identity mockIdentity;


    @Before
    public void Setup() throws NotBoundException {
        mockServerFuncs = mock(ServerFuncsImpl.class);
        messageService = new MessageService();
        mockIdentity = mock(Identity.class);
        mockMessages = new ArrayList<Message>();
        mockRegistryFuncs = mock(Registry.class);

    }
    @Test
    public void TestMessagesNotEmpty() throws RemoteException {
        String message = "Test message";
        Timestamp timestamp = Timestamp.from(Instant.now());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String formattedTime = formatter.format(timestamp);
        Message mockMsg1 = new Message(mockIdentity.getUsername(),formattedTime,message + '1');
        Message mockMsg2 = new Message(mockIdentity.getUsername(),formattedTime,message + '2');
        Message mockMsg3 = new Message(mockIdentity.getUsername(),formattedTime,message + '3');
        mockMessages.add(mockMsg1);
        mockMessages.add(mockMsg2);
        mockMessages.add(mockMsg3);
        when(mockServerFuncs.fetchMessages()).thenReturn(mockMessages);
        ChatRefresh chatRefresh = new ChatRefresh(mockChatAusgabe);
        chatRefresh.run();
        assertEquals(mockChatAusgabe.getText(),mockMsg1.sender + " | " + mockMsg1.date.toString() + " | " + mockMsg1.content
                + "\n");
    }

}
