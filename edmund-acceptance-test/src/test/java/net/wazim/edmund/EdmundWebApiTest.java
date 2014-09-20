package net.wazim.edmund;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import com.googlecode.yatspec.state.givenwhenthen.InterestingGivens;
import net.wazim.edmund.client.EdmundHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;

@RunWith(SpecRunner.class)
public class EdmundWebApiTest extends EdmundTestState {

    @Test
    public void edmundFindsAnEasyWordAtTheTopOfTheFile() throws Exception {
        given(edmundIsRunning());

        when(theUserRequestsTheApi(withThePattern("a."), andLengthOfTheWord(2)));

        then(theStatusCode(), is(200));
        then(theResponse(), matchesTheExpectedValue("aa"));
    }

    private int andLengthOfTheWord(int lengthOfWord) {
        return lengthOfWord;
    }

    private String withThePattern(String thePattern) {
        return thePattern;
    }

    private ActionUnderTest theUserRequestsTheApi(final String pattern, final int lengthOfTheWord) {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                EdmundHttpClient client = new EdmundHttpClient();
                client.addQueryParameter("pattern", pattern);
                client.addQueryParameter("length", String.valueOf(lengthOfTheWord));
                response = client.getRequest("http://localhost:" + localPort + "/edmund/api");
                interestingGivens.add("Query Pattern Parameter", pattern);
                interestingGivens.add("Query Length Parameter", lengthOfTheWord);
                interestingGivens.add("Http Response Code", response.code());
                interestingGivens.add("Http Response Body", response.body());
                capturedInputAndOutputs.add("Response Page", response.body());
                return capturedInputAndOutputs;
            }
        };
    }
}