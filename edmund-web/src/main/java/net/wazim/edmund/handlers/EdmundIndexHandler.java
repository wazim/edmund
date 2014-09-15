package net.wazim.edmund.handlers;

import net.wazim.edmund.DictionaryRepository;
import net.wazim.edmund.WordFinder;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EdmundIndexHandler extends AbstractHandler {

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        dictionaryRepository.readFromFile("/Users/jonathansharifi/Downloads/edmund/edmund-core/src/main/resources/dictionary.txt");

        WordFinder wordFinder = new WordFinder(dictionaryRepository);
        String solvedWord = wordFinder.solvePuzzle(httpServletRequest.getParameter("character"), Integer.parseInt(httpServletRequest.getParameter("length")));

        httpServletResponse.getWriter().println("<h1>Solved Word is " + solvedWord + "</h1>");
    }

}
