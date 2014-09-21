package net.wazim.edmund.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;

import java.util.List;

public class DocumentBuilder {

    public static String createXml(List<String> matchedWords) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("result");

        for (String matchedWord : matchedWords) {
            root.addElement("word")
                    .addText(matchedWord);
        }

        return document.asXML();
    }

    public static String createJson(List<String> matchedWords) {
        return new JSONObject().put("words", matchedWords).toString();
    }

}
