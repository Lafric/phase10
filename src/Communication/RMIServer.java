package Communication;

import Communication.ServerFuncsImpl;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] arrstring) {
        System.setProperty("java.rmi.server.hostname", "185.162.248.237");
        try {
            ServerFuncsImpl serverFuncsImpl = new ServerFuncsImpl();
            System.out.println("Created Remote Object");
            Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
            System.out.println("Found registry");
            registry.bind("serverfunc", (Remote)serverFuncsImpl);
            System.out.println("Server ready");
        }
        catch (Exception exception) {
            System.out.println("Some error..." + exception);
        }
    }
}
