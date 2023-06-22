package Communication;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;




public class RMIServer implements Runnable {

    @Override
    public void run() {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            ServerFuncsImpl serverfuncs = new ServerFuncsImpl();
            ServerFuncs ServerfuncsObj = (ServerFuncs) UnicastRemoteObject.exportObject(serverfuncs, 0);
            registry.bind("serverfunc", ServerfuncsObj);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
