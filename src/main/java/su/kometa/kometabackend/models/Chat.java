package su.kometa.kometabackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @OneToOne(mappedBy = "user_id", fetch = FetchType.EAGER)
    private User user;

    @OneToOne(mappedBy = "model_id", fetch = FetchType.EAGER)
    private Model model;

    @Column
    @OneToMany(mappedBy = "message_id", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Message> messages;
}
