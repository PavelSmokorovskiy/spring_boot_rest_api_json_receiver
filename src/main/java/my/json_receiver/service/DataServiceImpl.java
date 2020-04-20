package my.json_receiver.service;

import my.json_receiver.model.DataModel;
import my.json_receiver.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public Iterable<DataModel> getAllData() {
        return dataRepository.findAll();
    }

    @Override
    public Optional<DataModel> getDataById(Long id) {
        return dataRepository.findById(id);
    }

    @Override
    public void save(DataModel dataModel) {
        dataRepository.save(dataModel);
    }

    @Override
    public void deleteAllData() {
        dataRepository.deleteAll();
    }
}
