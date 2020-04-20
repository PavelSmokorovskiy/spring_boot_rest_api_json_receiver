package my.json_receiver;

import my.json_receiver.model.DataModel;
import my.json_receiver.repository.DataRepository;
import my.json_receiver.service.DataService;
import my.json_receiver.service.MapFlattener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonReceiverControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private DataService dataService;

    private final String sqlQuery = "SELECT 'json' FROM db.data_model_json WHERE (`data_model_id` = '1') and (`json_key` = 'json.key1');";

    private DataModel getDataModel() {

        Map<String, Object> json = new LinkedHashMap<>();
        json.put("key1", "value1");
        json.put("key2", "value2");
        json.put("key3", new HashMap<String, String>() {{
            put("internalKey1", "internalValue1");
            put("internalKey2", "internalValue2");
        }});
        Map<String, String> flatJson = MapFlattener.flattener(json);
        return DataModel.builder().json(flatJson).build();
    }

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    private URI getRootUri() {
        try {
            return new URI(getRootUrl() + "/json");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllData() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/json",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetDataById() {

        restTemplate.postForEntity(getRootUrl() + "/json", getDataModel(), DataModel.class);
        DataModel dataModel = restTemplate.getForObject(getRootUrl() + "/json/1", DataModel.class);
        assertNotNull(dataModel);

        Iterable<DataModel> allData = dataRepository.findAll();
        long id = ((DataModel) ((List) allData).get(0)).getId();
        assertNotEquals(id, 0);

        assertFalse(isDBEmpty());
    }

    @Test
    public void testReceiveData() {

        dataRepository.deleteAll();
        assertTrue(isDBEmpty());

        ResponseEntity<DataModel> postResponse =
                restTemplate.postForEntity(getRootUrl() + "/json", getDataModel(), DataModel.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals("201 CREATED", postResponse.getStatusCode().toString());
        assertEquals("application/json", Objects.requireNonNull(postResponse.getHeaders().get("Content-Type")).get(0));

        assertFalse(isDBEmpty());
    }


    @Test
    public void testDeleteAllData() {

        dataRepository.deleteAll();
        assertTrue(isDBEmpty());

        restTemplate.postForEntity(getRootUrl() + "/json", getDataModel(), DataModel.class);
        assertFalse(isDBEmpty());

        restTemplate.delete(getRootUri());
        assertTrue(isDBEmpty());
    }

    private Boolean isDBEmpty() {

        Iterable<DataModel> allData = dataRepository.findAll();
        return ((List) allData).size() == 0;
    }
}

