import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.SpecRunner;
import net.wazim.edmund.DictionaryRepository;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpecRunner.class)
public class DictionaryTest {

    private String firstWord;

    @Test
    @Notes("[Jon] As of right now, you need to manually update the path to the dictionary...")
    public void readsTheDictionaryFileAndParsesTheFirstLine() {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        dictionaryRepository.readFromFile("/Users/jonathansharifi/Downloads/edmund/src/main/resources/dictionary.txt");

        firstWord = dictionaryRepository.retrieveWordByIndex(0);

        assertThat("aa", matchesTheFirstWord());
    }

    public Matcher<String> matchesTheFirstWord() {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String s) {
                return firstWord.equals(s);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("did not match " + firstWord);
            }
        };
    }
}
