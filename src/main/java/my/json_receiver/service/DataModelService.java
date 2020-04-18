package my.json_receiver.service;

import my.json_receiver.model.DataModel;

public interface DataModelService {

    Iterable<DataModel> getAllData();

    void save(DataModel dataModel);
}
