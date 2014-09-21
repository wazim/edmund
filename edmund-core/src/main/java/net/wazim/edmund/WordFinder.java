package net.wazim.edmund;

import java.util.ArrayList;
import java.util.List;

public class WordFinder {

    private DictionaryRepository dictionaryRepository;

    public WordFinder(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public List<String> solvePuzzle(String pattern) {
        ArrayList<String> listOfMatchedWords = new ArrayList<String>();
        for (String word : dictionaryRepository.dictionary()) {
            if(word.toLowerCase().matches(pattern.toLowerCase()) &&  word.length() == pattern.length()) {
                 listOfMatchedWords.add(word);
            }
        }
        return listOfMatchedWords;
    }

}
