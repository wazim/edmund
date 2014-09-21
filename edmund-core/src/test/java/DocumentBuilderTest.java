import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static net.wazim.edmund.utils.DocumentBuilder.createJson;
import static net.wazim.edmund.utils.DocumentBuilder.createXml;
import static org.junit.Assert.assertEquals;

public class DocumentBuilderTest {

    private final List<String> matchedWords = asList("HELLO", "WORLD");

    @Test
    public void createsValidXmlDocument() {
        String actualXml = createXml(matchedWords);
        String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<result><word>HELLO</word><word>WORLD</word></result>";
        assertEquals(expectedXml, actualXml);
    }

    @Test
    public void createsValidJson() {
        String actualJson = createJson(matchedWords);
        String expectedJson = "{\"words\":[\"HELLO\",\"WORLD\"]}";
        assertEquals(expectedJson, actualJson);
    }

}
