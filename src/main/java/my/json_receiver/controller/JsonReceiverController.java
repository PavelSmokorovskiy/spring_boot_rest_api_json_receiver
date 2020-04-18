package my.json_receiver.controller;

import my.json_receiver.model.DataModel;
import my.json_receiver.service.DataModelService;
import my.json_receiver.service.Flattener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller for receiving JSON
 */
@RestController
@RequestMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class JsonReceiverController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataModelService dataModelService;

    /**
     * Get all data
     *
     * @link http://localhost:8080/json/
     */
    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, ?> getAllData() {

        Iterable<DataModel> allData = dataModelService.getAllData();
        Map<String, Object> result = new LinkedHashMap<>();
        allData.forEach(data -> result.put(data.getId().toString(), data.getJson()));
        logger.info("jsonAllData {}", result);
        return result;
    }

    /**
     * Get data by Id
     *
     * @link http://localhost:8080/json/
     */
    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, ?> getDataById(@PathVariable long id) {

        Optional<DataModel> data = dataModelService.getDataById(id);
        Map<String, Object> result = new LinkedHashMap<>();
        data.ifPresent(dataModel -> result.put(dataModel.getId().toString(), dataModel.getJson()));
        logger.info("jsonData id: {}: {}", id, result);
        return result;
    }

    /**
     * Persist JSON data to DB
     *
     * @link http://localhost:8080/json/
     */
    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String, String> receiveData(@RequestBody Map<String, ?> json) {

        Map<String, String> flatJson = Flattener.flattener(json);
        DataModel dataModel = DataModel.builder().json(flatJson).build();
        dataModelService.save(dataModel);
        logger.info("json received in Db: {}", dataModel);
        return flatJson;
    }

    /**
     * Delete all data
     *
     * @link http://localhost:8080/json/
     */
    @DeleteMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteAllData() {
        dataModelService.deleteAllData();
        logger.info("Deleted All json Data");
    }
}
