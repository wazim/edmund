import net.wazim.edmund.DictionaryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DictionaryTest {

    @Test
    public void readsTheDictionaryFileAndParsesTheFirstLine() {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        dictionaryRepository.readFromFile("/Users/jonathansharifi/Downloads/edmund/edmund-core/src/main/resources/dictionary.txt");

        String firstWord = dictionaryRepository.retrieveWordByIndex(0);

        assertEquals("aa", firstWord);
    }

}
