package net.wazim.edmund;

public class WordFinder {

    private DictionaryRepository dictionaryRepository;

    public WordFinder(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public String solvePuzzle(String firstCharacter, int lengthOfWord) {
        for (String word : dictionaryRepository.dictionary()) {
            if(word.toLowerCase().startsWith(firstCharacter.toLowerCase()) &&  word.length() == lengthOfWord) {
                return word;
            }
        }
        return null;
    }

}
