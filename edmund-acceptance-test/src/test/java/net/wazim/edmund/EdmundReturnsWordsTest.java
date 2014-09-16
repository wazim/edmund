package net.wazim.edmund;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
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

        when(theUserRequestsEdmund());

        then(theStatusCode(), is(200));
    }

    @Test
    public void edmundFindsAnEasyWordAtTheTopOfTheFile() throws Exception {
        given(edmundIsRunning());

        when(theUserAsksToSolveAPuzzle(withTheLetter("a"), andLengthOfTheWord(2)));

        then(theResponse(), is("aa"));
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

    private String withTheLetter(String theLetter) {
        return theLetter;
    }

    private StateExtractor<String> theResponse() {
        return new StateExtractor<String>() {
            @Override
            public String execute(CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return responseBody.trim();
            }
        };
    }

    private ActionUnderTest theUserAsksToSolveAPuzzle(final String character, final int lengthOfTheWord) {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                HttpClient httpClient = new HttpClient();
                HttpMethod method = new GetMethod("http://localhost:" + localPort);
                method.setQueryString(new NameValuePair[]{
                        new NameValuePair("character", character),
                        new NameValuePair("length", String.valueOf(lengthOfTheWord))
                });
                response = httpClient.executeMethod(method);
                responseBody = method.getResponseBodyAsString();

                interestingGivens.add("Query Character Parameter", character);
                interestingGivens.add("Query Length Parameter", lengthOfTheWord);
                interestingGivens.add("Http Response Code", response);
                return capturedInputAndOutputs;
            }
        };
    }

    private ActionUnderTest theUserRequestsEdmund() {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                HttpClientParams params = new HttpClientParams();
                HttpClient httpClient = new HttpClient(params);
                HttpMethod method = new GetMethod("http://localhost:" + localPort);
                response = httpClient.executeMethod(method);

                interestingGivens.add("Http Response Code", response);
                return capturedInputAndOutputs;
            }
        };
    }
}
