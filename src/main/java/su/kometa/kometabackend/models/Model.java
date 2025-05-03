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
    private long id;

    @Column
    private String name;

    @Column
    private String provider;

    @Column
    private long maxTokensPerChat;

    @Column
    private boolean enabled;

    @Column
    private String apiKey;

    @Transient
    private String apiURL;

    public Model(String name, String provider, boolean enabled) {

    }
}
