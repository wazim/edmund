package net.wazim.edmund;

import net.wazim.edmund.controllers.EdmundApiServlet;
import net.wazim.edmund.controllers.EdmundHealthServlet;
import net.wazim.edmund.controllers.EdmundIndexServlet;
import net.wazim.edmund.controllers.EdmundRetrieverServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class EdmundRunner {

    private Server server = null;

    public EdmundRunner(int localPort) {
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
        handler.addServletWithMapping(EdmundApiServlet.class, "/edmund/api");
        handler.addServletWithMapping(EdmundRetrieverServlet.class, "/edmund/search");
        handler.addServletWithMapping(EdmundHealthServlet.class, "/edmund/health");

        server.start();
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("org.eclipse.jetty.LEVEL", "DEBUG");
        new EdmundRunner(15296).start();
    }

    public boolean isRunning() {
        return server.isRunning();
    }
}
