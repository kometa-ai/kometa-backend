package su.kometa.kometabackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "model_id")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Model model;

    @Column
    private String content;
}
