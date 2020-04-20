package my.json_receiver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static my.json_receiver.service.MapFlattener.flattener;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MapFlattenerJunitTest {

    private Map<String, ?> getMultidimensionalDataModel() {

        return new LinkedHashMap<>() {{
            put("key1", "value1");
            put("key2", "value2");
            put("key3", new HashMap<String, Object>() {
                {
                    put("internalKey1", "internalValue1");
                    put("internalKey2", "internalValue2");
                    put("key4", new HashMap<String, Object>() {{
                        put("internalKey3", "internalValue3");
                        put("key5", new HashMap<String, String>() {{
                            put("internalKey4", "internalValue4");
                            put("internalKey5", "internalValue5");
                        }});
                    }});
                }
            });
        }};
    }

    @Test
    public void testFlattener() {
        Map<String, String> res = flattener(getMultidimensionalDataModel());
        assertTrue(res.toString().contains("key3.key4.internalKey3"));
        assertTrue(res.toString().contains("key3.key4.key5.internalKey5"));
    }
}
