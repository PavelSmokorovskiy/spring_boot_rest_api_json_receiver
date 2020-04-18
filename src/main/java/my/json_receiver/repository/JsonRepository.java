package my.json_receiver.repository;

import my.json_receiver.model.DataModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
@Repository
public interface JsonRepository extends CrudRepository<DataModel, Long> {

}
