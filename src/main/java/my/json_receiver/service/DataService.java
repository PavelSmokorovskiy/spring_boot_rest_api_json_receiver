package my.json_receiver.service;

import my.json_receiver.model.DataModel;

import java.util.Optional;

public interface DataService {

    Iterable<DataModel> getAllData();

    Optional<DataModel> getDataById(Long id);

    void save(DataModel dataModel);

    void deleteAllData();
}
