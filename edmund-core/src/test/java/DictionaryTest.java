import net.wazim.edmund.DictionaryRepository;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class DictionaryTest {

    @Test
    public void readsTheDictionaryFileAndParsesTheFirstLine() {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        URL url = ClassLoader.getSystemResource("dictionary.txt");
        String file = url.getPath();
        dictionaryRepository.readFromFile(file);

        String firstWord = dictionaryRepository.retrieveWordByIndex(0);

        assertEquals("aa", firstWord);
    }

}
