package su.kometa.kometabackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Exclude;

@Entity
@Data
@NoArgsConstructor
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column
    public String name;

    @Column
    public String provider;

    @Transient
    public String apiKey;

    @Transient
    public String apiURL;

    public Model(String name, String provider) {

    }
}
