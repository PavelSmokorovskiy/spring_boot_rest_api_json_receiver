package my.json_receiver.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON entity
 */
@Entity
@Table()
public class DataModel {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private Map<String, String> json;

    public DataModel() {
    }

    public static Builder builder() {
        return new DataModel().new Builder();
    }

    public class Builder {

        public Builder() {
        }

        public Builder json(Map<String, String> map) {
            DataModel.this.json = map != null ? map : new HashMap<>();
            return this;
        }

        public DataModel build() {
            return DataModel.this;
        }
    }

    public Long getId() {
        return id;
    }

    public Map<String, String> getJson() {
        return json;
    }
}
