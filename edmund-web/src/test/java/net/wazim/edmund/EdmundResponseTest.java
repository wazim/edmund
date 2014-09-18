package net.wazim.edmund;

import net.wazim.edmund.model.EdmundResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EdmundResponseTest {

    @Test
    public void edmundResponseHasException() {
        EdmundResponse response = new EdmundResponse(new IOException());
        assertTrue(response.hasException());
    }

    @Test
    public void edmundHasSuccessfulHttpResponse() {
        EdmundResponse response = new EdmundResponse(200, "OK");
        assertFalse(response.hasException());
        assertEquals(response.code(), 200);
        assertEquals(response.body(), "OK");
    }
}
