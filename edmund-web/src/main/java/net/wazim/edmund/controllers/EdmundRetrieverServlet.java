package net.wazim.edmund.controllers;

import net.wazim.edmund.DictionaryRepository;
import net.wazim.edmund.WordFinder;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

public class EdmundRetrieverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        DictionaryRepository dictionaryRepository = new DictionaryRepository();

        InputStream url = ClassLoader.getSystemResourceAsStream("dictionary.txt");
        List<String> listOfWords = IOUtils.readLines(url, Charset.defaultCharset());
        dictionaryRepository.setListOfWords(listOfWords);

        WordFinder wordFinder = new WordFinder(dictionaryRepository);

        String pattern = req.getParameter("pattern");
        long startTime = System.currentTimeMillis();

        if (pattern.length() > 0) {
            List<String> listOfFoundWords = wordFinder.solvePuzzle(pattern);
            resp.getWriter().println("<head><title>Edmund</title><body>");
            resp.getWriter().println("<h3>You are looking for a word of <font color=\"red\">" + pattern.length() + "</font> characters in length that matches the pattern of <font color=\"red\">" + pattern + "</font></h3>");
            resp.getWriter().println("<h3>Edmund thinks these words are what you are looking for:</h3><ul>");
            for (String matchedWord : listOfFoundWords) {
                resp.getWriter().println("<li>" + matchedWord + "</li>");
            }
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime);
            resp.getWriter().println("</ul></br>This request took Edmund " + duration + " ms to complete");
            resp.getWriter().println("</body>");
        } else {
            resp.getWriter().println("You must provide a valid length");
        }
    }

}
