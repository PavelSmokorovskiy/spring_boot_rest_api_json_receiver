package my.json_receiver.controller;

import my.json_receiver.config.Logging;
import my.json_receiver.model.DataModel;
import my.json_receiver.service.DataModelService;
import my.json_receiver.service.Flattener;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for receiving JSON
 */
@RestController
@RequestMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class JsonReceiverController {

    private final Logging logger = new Logging(LogManager.getLogger(this.getClass()));

    @Autowired
    private DataModelService dataModelService;

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, ?> getAllData() {

        Iterable<DataModel> allData = dataModelService.getAllData();
        Map<String, Object> result = new LinkedHashMap<>();
        allData.forEach(data -> result.put(data.getId().toString(), data.getJson()));
        return result;
    }

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, ?> getDataById(@PathVariable long id) {

        Optional<DataModel> data = dataModelService.getDataById(id);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(data.get().getId().toString(), data.get().getJson());
        return result;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String, String> receiveData(@RequestBody Map<String, ?> json) {

        Map<String, String> flatJson = Flattener.flattener(json);
        DataModel dataModel = DataModel.builder().json(flatJson).build();
        dataModelService.save(dataModel);
        return flatJson;
    }

    @DeleteMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteAllData(){
        dataModelService.deleteAllData();
    }
}
