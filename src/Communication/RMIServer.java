package Communication;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer implements Runnable {

    @Override
    public void run() {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("serverfunc", new ServerFuncsImpl());
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
