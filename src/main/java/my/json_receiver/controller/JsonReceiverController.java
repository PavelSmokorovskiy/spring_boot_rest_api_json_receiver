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
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * REST controller for receiving JSON
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class JsonReceiverController {

    private final Logging logger = new Logging(LogManager.getLogger(this.getClass()));

    @Autowired
    private DataModelService dataModelService;

    @GetMapping(value = "json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getData() {

        Iterable<DataModel> allData = dataModelService.getAllData();
        return "test";
    }

    @PostMapping(value = "json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String receiveData(@RequestBody Map<String, ?> json) {

        Map<String, String> flatJson = Flattener.flattener(json);
        DataModel dataModel = DataModel.builder().json(flatJson).build();
        dataModelService.save(dataModel);
        return flatJson.toString();
    }


}
