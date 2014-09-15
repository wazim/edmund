import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.InterestingGivens;
import net.wazim.edmund.DictionaryRepository;
import net.wazim.edmund.WordFinder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;

@RunWith(SpecRunner.class)
public class WordSolverTest {

    private String solvedWord;
    private InterestingGivens capturedInputAndOutputs;

    @Before
    public void setup() {
        capturedInputAndOutputs = new InterestingGivens();
    }

    @Test
    @Notes("[Jon] As of right now, you need to manually update the path to the dictionary...")
    public void correctlyChoosesTheCorrectWordUsingOnlyOneLetter() {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        dictionaryRepository.readFromFile("/Users/jonathansharifi/Downloads/edmund/src/test/resources/test-dictionary.txt");

        WordFinder wordFinder = new WordFinder(dictionaryRepository);
        solvedWord = wordFinder.solvePuzzle(withLetter("M"), ofLength(7));

        assertThat("monster", matchesTheSolvedWord());
    }

    public Matcher<String> matchesTheSolvedWord() {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String expectedWord) {
                capturedInputAndOutputs.add("Word to Match", expectedWord);
                capturedInputAndOutputs.add("Found Word", solvedWord);
                return expectedWord.equals(solvedWord);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("did not match "+ solvedWord);
            }
        };
    }

    private int ofLength(int numberOfCharacters) {
        return numberOfCharacters;
    }

    private String withLetter(String firstCharacter) {
        return firstCharacter;
    }


}
