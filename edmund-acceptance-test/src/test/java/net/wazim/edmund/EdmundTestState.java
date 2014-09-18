package net.wazim.edmund;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.*;
import net.wazim.edmund.model.EdmundResponse;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(SpecRunner.class)
public class EdmundTestState extends TestState {

    protected EdmundRunner edmundRunner;
    protected int localPort = 11691;
    protected EdmundResponse response;

    @Before
    public void setUp() throws Exception {
        // System.setProperty("org.eclipse.jetty.LEVEL", "DEBUG"); // [Jon] Uncomment this if you want a higher level of logging by Jetty
        edmundRunner = new EdmundRunner(localPort);
        edmundRunner.start();
    }

    @After
    public void tearDown() {
        edmundRunner.stopEverything();
    }

    protected GivensBuilder edmundIsRunning() {
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

    protected StateExtractor<Integer> theStatusCode() {
        return new StateExtractor<Integer>() {
            @Override
            public Integer execute(CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return response.code();
            }
        };
    }

    protected StateExtractor<String> theResponse() {
        return new StateExtractor<String>() {
            @Override
            public String execute(CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                return response.body();
            }
        };
    }

    protected Matcher<String> matchesTheExpectedValue(final String expectedValue) {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String theResponse) {
                return theResponse.contains(expectedValue);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Does not contain the expected value of: "+expectedValue);
            }
        };
    }
}
