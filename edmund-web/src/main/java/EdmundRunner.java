import net.wazim.edmund.handlers.EdmundIndexHandler;
import org.eclipse.jetty.server.Server;

public class EdmundRunner {

    private final Server server;

    public EdmundRunner() {
        System.out.println("Starting Edmund...");
        server = new Server(12559);
        server.setHandler(new EdmundIndexHandler());

        try {
            server.start();
            System.out.println("Edmund has awoken...");
            server.join();
        } catch (Exception e) {
            System.out.println("Failed to start Edmund..." + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new EdmundRunner();
    }

    public void stopEverything() {
        server.destroy();
        try {
            server.stop();
        } catch (Exception e) {
            System.out.println("Edmund cannot be stopped!");
            e.printStackTrace();
        }
    }

}
