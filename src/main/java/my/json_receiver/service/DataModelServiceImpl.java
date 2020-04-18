package my.json_receiver.service;

import my.json_receiver.model.DataModel;
import my.json_receiver.repository.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataModelServiceImpl implements DataModelService {

    @Autowired
    private JsonRepository jsonRepository;

    @Override
    public Iterable<DataModel> getAllData() {
        return jsonRepository.findAll();
    }

    @Override
    public Optional<DataModel> getDataById(long id) {
        return jsonRepository.findById(id);
    }

    @Override
    public void save(DataModel dataModel) {
        jsonRepository.save(dataModel);
    }

    @Override
    public void deleteAllData() {
        jsonRepository.deleteAll();
    }
}
