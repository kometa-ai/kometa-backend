package su.kometa.kometabackend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private User user;

    @JoinColumn(name = "model_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Model model;

    @JoinColumn(name = "chat_id")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Chat chat;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "parent_message_id")
    private Message parentMessage;

    @Column
    private String content;

    @Column
    private long timestamp;
    
    public Message(User user, Model model, Chat chat, String content, Message parentMessage) {
        this.user = user;
        this.model = model;
        this.chat = chat;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.parentMessage = parentMessage;
    }
}
