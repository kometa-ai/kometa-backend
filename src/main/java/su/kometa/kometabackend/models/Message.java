package su.kometa.kometabackend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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

    @JoinColumn(name = "chat_id")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Chat chat;

    @Column
    private String content;
    
    public Message(User user, Model model, Chat chat, String content) {
        this.user = user;
        this.model = model;
        this.chat = chat;
        this.content = content;
    }
}
