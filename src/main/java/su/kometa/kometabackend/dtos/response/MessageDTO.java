package su.kometa.kometabackend.dtos.response;

import lombok.Data;
import su.kometa.kometabackend.models.Message;
import su.kometa.kometabackend.models.Model;
import su.kometa.kometabackend.models.User;

@Data
public class MessageDTO {

    private long id;

    private User user;

    private Model model;

    private String content;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.user = message.getUser();
        this.model = message.getModel();
        this.content = message.getContent();
    }
}
