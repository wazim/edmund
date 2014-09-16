package net.wazim.edmund;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;

@RunWith(SpecRunner.class)
public class EdmundReturnsWordsTest extends EdmundTestState {

    private int response;
    private String responseBody;

    @Test
    public void edmundReturnsStatusCode200WhenRunningOkay() throws Exception {
        given(edmundIsRunning());

        when(theUserRequestsEdmundHealthPage());

        then(theStatusCode(), is(200));
        then(theResponse(), matchesTheExpectedValue("Edmund Lives"));
    }

    @Test
    public void edmundFindsAnEasyWordAtTheTopOfTheFile() throws Exception {
        given(edmundIsRunning());

        when(theUserAsksToSolveAPuzzle(withThePattern("a."), andLengthOfTheWord(2)));

        then(theStatusCode(), is(200));
        then(theResponse(), matchesTheExpectedValue("aa"));
    }

    @Test
    public void edmundFindsAnADifficultWordInTheMiddleOfTheFile() throws Exception {
        given(edmundIsRunning());

        when(theUserAsksToSolveAPuzzle(withThePattern("...s..."), andLengthOfTheWord(7)));

        then(theStatusCode(), is(200));
        then(theResponse(), matchesTheExpectedValue("monster"));
    }

    private StateExtractor<Integer> theStatusCode() {
        return new StateExtractor<Integer>() {
            @Override
            public Integer execute(CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return response;
            }
        };
    }

    private GivensBuilder edmundIsRunning() {
        return new GivensBuilder() {
            @Override
            public InterestingGivens build(InterestingGivens interestingGivens) throws Exception {
                while(!edmundRunner.isRunning()){
                    Thread.sleep(100);
                }
                return interestingGivens;
            }
        };
    }

    private int andLengthOfTheWord(int lengthOfWord) {
        return lengthOfWord;
    }

    private String withThePattern(String thePattern) {
        return thePattern;
    }

    private StateExtractor<String> theResponse() {
        return new StateExtractor<String>() {
            @Override
            public String execute(CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return responseBody.trim();
            }
        };
    }

    private ActionUnderTest theUserAsksToSolveAPuzzle(final String pattern, final int lengthOfTheWord) {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                HttpClient httpClient = new HttpClient();
                HttpMethod method = new GetMethod("http://localhost:" + localPort + "/edmund");
                method.setQueryString(new NameValuePair[]{
                        new NameValuePair("pattern", pattern),
                        new NameValuePair("length", String.valueOf(lengthOfTheWord))
                });
                response = httpClient.executeMethod(method);
                responseBody = method.getResponseBodyAsString();

                interestingGivens.add("Query Pattern Parameter", pattern);
                interestingGivens.add("Query Length Parameter", lengthOfTheWord);
                interestingGivens.add("Http Response Code", response);
                interestingGivens.add("Http Response Body", responseBody);
                return capturedInputAndOutputs;
            }
        };
    }

    private ActionUnderTest theUserRequestsEdmundHealthPage() {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                HttpClient httpClient = new HttpClient();
                HttpMethod method = new GetMethod("http://localhost:" + localPort + "/edmund/health");
                response = httpClient.executeMethod(method);
                responseBody = method.getResponseBodyAsString();

                interestingGivens.add("Http Response Code", response);
                return capturedInputAndOutputs;
            }
        };
    }
}
