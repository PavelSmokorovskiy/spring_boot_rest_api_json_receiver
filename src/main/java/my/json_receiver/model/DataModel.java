package my.json_receiver.model;

import javax.persistence.*;
import java.util.Map;

/**
 * JSON entity
 */
@Entity
@Table()
public class DataModel {

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private Map<String, String> json;

    public DataModel() {
    }

    public DataModel(Long id, Map<String, String> json) {
        this.id = id;
        this.json = json;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getJson() {
        return json;
    }

    public void setJson(Map<String, String> map) {
        this.json = map;
    }
}
