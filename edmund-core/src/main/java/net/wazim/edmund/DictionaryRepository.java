package net.wazim.edmund;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryRepository {

    private List<String> listOfWords = new ArrayList<String>();

    public void setListOfWords(List<String> newListOfWords) {
        listOfWords = newListOfWords;
    }

    public void readFromFile(String fileSource) {
        File file = new File(fileSource);
        try {
            listOfWords = FileUtils.readLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String retrieveWordByIndex(int index) {
        return listOfWords.get(index);
    }

    public List<String> dictionary() {
        return listOfWords;
    }

}
