package su.kometa.kometabackend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column
    public long maxTokensPerChat;

    @Column
    public boolean enabled;

    @Column
    public String apiKey;

    @Transient
    public String apiURL;

    public Model(String name, String provider, boolean enabled) {

    }
}
