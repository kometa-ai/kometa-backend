package su.kometa.kometabackend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "configs")
public class Config {

    @Id
    @Column
    private String key;

    @Column
    private String value;
}
