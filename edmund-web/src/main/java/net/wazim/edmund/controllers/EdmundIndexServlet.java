package net.wazim.edmund.controllers;

import net.wazim.edmund.DictionaryRepository;
import net.wazim.edmund.WordFinder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EdmundIndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        dictionaryRepository.readFromFile("/Users/jonathansharifi/Downloads/edmund/edmund-core/src/main/resources/dictionary.txt");

        WordFinder wordFinder = new WordFinder(dictionaryRepository);

        String character = req.getParameter("character");
        int lengthParameter;
        try {
            lengthParameter = Integer.parseInt(req.getParameter("length"));
        } catch(Exception e){
            lengthParameter = 0;
        }

        if(lengthParameter > 0) {
            String solvedWord = wordFinder.solvePuzzle(character, lengthParameter);
            resp.getWriter().println(solvedWord);
        }
        else{
            resp.getWriter().println("You must provide a valid length");
        }
    }

}
