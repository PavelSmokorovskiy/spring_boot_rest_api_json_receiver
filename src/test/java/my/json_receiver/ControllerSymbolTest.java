package my.json_receiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ControllerSymbolTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("test '{'")
    void testGetAllDataSymbol() {
        String message = this.restTemplate.getForObject("/json", String.class);
        assertTrue(message.contains("{"));
    }

    @Test
    @DisplayName("test '{'")
    void testGetDataByIdSymbol() {
        String message0 = this.restTemplate.getForObject("/json/0", String.class);
        String message1 = this.restTemplate.getForObject("/json/1", String.class);
        assertTrue(message0.contains("{"));
        assertTrue(message1.contains("{"));
    }
}
