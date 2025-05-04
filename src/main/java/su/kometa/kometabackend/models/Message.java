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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reply_message_id")
    private Message replyMessage;

    @Column
    private String content;

    @Column
    private long timestamp;
    
    public Message(User user, Model model, Chat chat, String content, Message replyMessage) {
        this.user = user;
        this.model = model;
        this.chat = chat;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.replyMessage = replyMessage;
    }
}
