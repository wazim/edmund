package net.wazim.edmund.controllers;

import net.wazim.edmund.DictionaryRepository;
import net.wazim.edmund.WordFinder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static net.wazim.edmund.utils.DocumentBuilder.createJson;
import static net.wazim.edmund.utils.DocumentBuilder.createXml;

public class EdmundApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        DictionaryRepository dictionaryRepository = new DictionaryRepository();

        URL url = ClassLoader.getSystemResource("dictionary.txt");
        String file = url.getPath();
        dictionaryRepository.readFromFile(file);

        WordFinder wordFinder = new WordFinder(dictionaryRepository);

        String pattern = req.getParameter("pattern");
        String format = req.getParameter("format");

        if (pattern.length() > 0) {
            List<String> listOfFoundWords = wordFinder.solvePuzzle(pattern);
            if (format.toLowerCase().equals("json")) {
                resp.setContentType("application/json");
                resp.getWriter().println(createJson(listOfFoundWords));
            } else {
                resp.setContentType("application/xml");
                resp.getWriter().println(createXml(listOfFoundWords));
            }
        } else {
            resp.getWriter().println("You must provide a valid length");
        }

    }

}
