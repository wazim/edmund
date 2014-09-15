import com.googlecode.yatspec.junit.Notes;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;


@RunWith(SpecRunner.class)
public class EdmundReturnsWordsTest extends TestState {

    @Test
    @Notes("[Jon] Work in progress while we configure the Spring web framework.")
    public void edmundFindsAWord() throws Exception {
        given(edmundIsRunning());

        when(theUserAsksToSolveAPuzzle(withTheLetter("M"), andLengthOfTheWord(5)));

        then(theResponse(), is("monster"));
    }

    private int andLengthOfTheWord(int lengthOfWord) {
        return lengthOfWord;
    }

    private String withTheLetter(String theLetter) {
        return theLetter;
    }

    private GivensBuilder edmundIsRunning() {
        return new GivensBuilder() {
            @Override
            public InterestingGivens build(InterestingGivens interestingGivens) throws Exception {
                return null;
            }
        };
    }

    private StateExtractor<String> theResponse() {
        return new StateExtractor<String>() {
            @Override
            public String execute(CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return "monster";
            }
        };
    }

    private ActionUnderTest theUserAsksToSolveAPuzzle(final String character, final int lengthOfTheWord) {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return null;
            }
        };
    }
}
