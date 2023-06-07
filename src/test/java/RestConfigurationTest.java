import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.vicary.RestConfiguration;

public class RestConfigurationTest {
    RestConfiguration restConfiguration;

    @BeforeEach
    public void setup() {
        restConfiguration = new RestConfiguration();
    }
    @Test
    public void restTemplate_ExpectRestTemplate() {
        //given
        RestTemplate restTemplateExpected = new RestTemplate();
        RestTemplate restTemplateActual = restConfiguration.restTemplate();
        Assertions.assertSame(restTemplateExpected.getClass(), restTemplateActual.getClass());
        Assertions.assertNotEquals(restTemplateExpected, restTemplateActual);
    }
}
