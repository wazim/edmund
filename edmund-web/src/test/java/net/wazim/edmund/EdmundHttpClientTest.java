package net.wazim.edmund;

import net.wazim.edmund.client.EdmundHttpClient;
import net.wazim.edmund.model.EdmundResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdmundHttpClientTest {

    @Test
    public void addQueryParametersToRequest() {
        EdmundHttpClient client = new EdmundHttpClient();
        client.addQueryParameter("pattern", "jonat.an");
        client.addQueryParameter("length", "7");

        assertEquals(client.queryParams().get(0).getName(), "pattern");
        assertEquals(client.queryParams().get(0).getValue(), "jonat.an");
        assertEquals(client.queryParams().get(1).getName(), "length");
        assertEquals(client.queryParams().get(1).getValue(), "7");
    }

    @Test
    public void makeGetRequestToUrl() {
        //TODO: Mock the response here...
        EdmundHttpClient client = new EdmundHttpClient();
        EdmundResponse response = client.getRequest("http://0.0.0.0");

        assertEquals(response.code(), 0);
        assertEquals(response.body(), null);
    }

    @Test
    public void makePostRequestToUrl() {
        //TODO: Mock the response here...
        EdmundHttpClient client = new EdmundHttpClient();
        EdmundResponse response = client.postRequest("http://0.0.0.0");

        assertEquals(response.code(), 0);
        assertEquals(response.body(), null);
    }
}
