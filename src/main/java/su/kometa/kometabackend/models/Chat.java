package su.kometa.kometabackend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "model_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Model model;

    @Column
    @OneToMany(mappedBy = "id", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Message> messages;

    public Chat(String title, User user, Model model) {
        this.title = title;
        this.user = user;
        this.model = model;
    }
}
