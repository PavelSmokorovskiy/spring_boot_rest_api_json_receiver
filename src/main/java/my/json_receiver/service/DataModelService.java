package my.json_receiver.service;

import my.json_receiver.model.DataModel;

import java.util.Optional;

public interface DataModelService {

    Iterable<DataModel> getAllData();

    Optional<DataModel> getDataById(long id);

    void save(DataModel dataModel);

    void deleteAllData();
}
