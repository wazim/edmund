package net.wazim.edmund;

import java.util.ArrayList;
import java.util.List;

public class WordFinder {

    private DictionaryRepository dictionaryRepository;

    public WordFinder(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public List<String> solvePuzzle(String firstCharacter, int lengthOfWord) {
        ArrayList<String> listOfMatchedWords = new ArrayList<String>();
        for (String word : dictionaryRepository.dictionary()) {
            if(word.toLowerCase().matches(firstCharacter.toLowerCase()) &&  word.length() == lengthOfWord) {
                 listOfMatchedWords.add(word);
            }
        }
        return listOfMatchedWords;
    }

}
