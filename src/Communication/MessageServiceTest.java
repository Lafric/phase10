/**package Communication;

import org.junit.Before;
import org.junit.Test;

import Model.Identity;

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

public class MessageServiceTest {

    private MessageService messageService;
    private ServerFuncs mockServerFuncs;
    private Identity mockIdentity;
    private ArrayList<Message> mockMessages;

    @Before
    public void setup() throws NotBoundException {
        mockServerFuncs = mock(ServerFuncs.class);
        messageService = new MessageService();
        mockIdentity = mock(Identity.class);
        mockMessages = new ArrayList<Message>();
    }

    @Test
    public void testSendeNachricht() throws Exception {
        String nachricht = "Test message";
        Timestamp timestamp = Timestamp.from(Instant.now());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String formattedTime = formatter.format(timestamp);
        Message expectedMessage = new Message(mockIdentity.getUsername(), formattedTime, nachricht);

        doAnswer(invocation -> {
            Message message = invocation.getArgument(0);
            mockMessages.add(message);
            return null;
        }).when(mockServerFuncs).sendMessageToChat(any(Message.class));

        when(mockServerFuncs.fetchMessages()).thenReturn(mockMessages);

        Message actualMessage = messageService.sendeNachricht(mockIdentity, nachricht);

        assertEquals(expectedMessage.content, actualMessage.content);
        assertEquals(expectedMessage.sender, actualMessage.sender);
        assertEquals(expectedMessage.date, actualMessage.date);

        verify(mockServerFuncs).sendMessageToChat(any(Message.class));
        verify(mockServerFuncs).fetchMessages();
    }

    private Registry getMockRegistry() throws NotBoundException {
        try {
            Registry mockRegistry = mock(Registry.class);
            when(mockRegistry.lookup("serverfunc")).thenReturn(mockServerFuncs);
            return mockRegistry;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
**/