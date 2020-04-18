package my.json_receiver.controller;

import my.json_receiver.config.Logging;
import my.json_receiver.model.DataModel;
import my.json_receiver.repository.JsonRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.vault.support.JsonMapFlattener;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for receiving JSON
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class JsonReceiverController {

    private final Logging logger = new Logging(LogManager.getLogger(this.getClass()));

    @Autowired
    private JsonRepository jsonRepository;

    @GetMapping(value = "json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getData(){
        return "test";
    }

    @PostMapping(value = "json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String receiveData(@RequestBody Map<String, ?> json){
        Map<String, String> flatJson = JsonMapFlattener.flattenToStringMap(json);

        DataModel test = new DataModel(1L, flatJson);
        jsonRepository.save(test);


        return flatJson.toString();
    }


}
