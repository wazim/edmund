package net.wazim.edmund;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
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

    @Before
    public void setUp() throws Exception {
        edmundRunner = new EdmundRunner(localPort);
        edmundRunner.start();
    }

    @After
    public void tearDown() {
        edmundRunner.stopEverything();
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
