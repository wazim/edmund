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
public class EdmundHealthCheckTest extends EdmundTestState {

    @Test
    public void edmundReturnsStatusCode200WhenRunningOkay() throws Exception {
        given(edmundIsRunning());

        when(theUserRequestsEdmundHealthPage());

        then(theStatusCode(), is(200));
        then(theResponse(), matchesTheExpectedValue("Edmund Lives"));
    }

    private ActionUnderTest theUserRequestsEdmundHealthPage() {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                EdmundHttpClient client = new EdmundHttpClient();
                response = client.getRequest("http://localhost:" + localPort + "/edmund/health");
                interestingGivens.add("Http Response Code", response.code());
                interestingGivens.add("Http Response Body", response.body());
                return capturedInputAndOutputs;
            }
        };
    }
}
