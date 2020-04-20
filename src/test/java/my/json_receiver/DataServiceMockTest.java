package my.json_receiver;

import my.json_receiver.model.DataModel;
import my.json_receiver.repository.DataRepository;
import my.json_receiver.service.DataService;
import my.json_receiver.service.MapFlattener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DataServiceMockTest {

    @Autowired
    private DataService dataService;

    @MockBean
    private DataRepository dataRepository;

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

    @BeforeEach
    public void setMockOut() {

        when(dataRepository.findById(1L)).thenReturn(Optional.of(getDataModel()));
        when(dataRepository.findAll()).thenReturn(Collections.singletonList(getDataModel()));
    }

    @Test
    public void testGetDataById() {

        Optional<DataModel> result = dataService.getDataById(1L);
        assertEquals(result.get().getJson().get("key1"), "value1");
    }

    @Test
    public void testGetAllData() {

        List<DataModel> allData = (List<DataModel>) dataService.getAllData();
        assertEquals(allData.get(0).getJson().get("key2"), "value2");
    }
}
