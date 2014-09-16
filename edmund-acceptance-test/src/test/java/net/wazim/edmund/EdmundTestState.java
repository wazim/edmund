package net.wazim.edmund;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(SpecRunner.class)
public class EdmundTestState extends TestState {

    protected EdmundRunner edmundRunner;
    protected int localPort = 11691;

    @After
    public void tearDown() {
        edmundRunner.stopEverything();
    }

    @Before
    public void setUp() throws Exception {
        edmundRunner = new EdmundRunner(localPort);
        edmundRunner.start();
    }
}
