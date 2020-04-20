package my.json_receiver.service;

import org.springframework.vault.support.JsonMapFlattener;

import java.util.Map;

public class MapFlattener {

    /**
     * Flatten a hierarchical Map into a flat Map with key names using property dot notation.
     *
     * @param map
     * @return Map<String, String>
     */
    public static Map<String, String> flattener(Map<String, ?> map) {
        return JsonMapFlattener.flattenToStringMap(map);
    }
}
