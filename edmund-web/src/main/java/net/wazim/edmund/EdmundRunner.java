package net.wazim.edmund;

import net.wazim.edmund.controllers.EdmundHealthServlet;
import net.wazim.edmund.controllers.EdmundIndexServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class EdmundRunner {

    private Server server = null;

    public EdmundRunner(int localPort) {
        // System.setProperty("org.eclipse.jetty.LEVEL", "DEBUG"); // [Jon] Uncomment this if you want a higher level of logging by Jetty
        server = new Server(localPort);
        System.out.println("Starting Edmund on " + localPort);
    }

    public void stopEverything() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        ServletHandler handler = new ServletHandler();

        server.setHandler(handler);

        handler.addServletWithMapping(EdmundIndexServlet.class, "/edmund");
        handler.addServletWithMapping(EdmundHealthServlet.class, "/edmund/health");

        server.start();
    }

    public static void main(String[] args) throws Exception {
        new EdmundRunner(15296).start();
    }

    public boolean isRunning() {
        return server.isRunning();
    }
}
