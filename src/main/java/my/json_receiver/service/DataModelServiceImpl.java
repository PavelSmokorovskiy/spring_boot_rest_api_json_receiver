package my.json_receiver.service;

import my.json_receiver.model.DataModel;
import my.json_receiver.repository.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataModelServiceImpl implements DataModelService {

    @Autowired
    private JsonRepository jsonRepository;

    @Override
    public void save(DataModel dataModel) {
        jsonRepository.save(dataModel);
    }
}