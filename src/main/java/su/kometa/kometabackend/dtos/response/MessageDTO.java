package su.kometa.kometabackend.dtos.response;

import lombok.Data;
import su.kometa.kometabackend.models.Message;
import su.kometa.kometabackend.models.Model;
import su.kometa.kometabackend.models.User;

@Data
public class MessageDTO {

    private long id;

    private UserDTO user;

    private ModelDTO model;

    private String content;

    public MessageDTO(Message message) {
        this.id = message.getId();
        if (message.getUser() != null) this.user = new UserDTO(message.getUser());
        if (message.getModel() != null) this.model = new ModelDTO(message.getModel());
        this.content = message.getContent();
    }
}
