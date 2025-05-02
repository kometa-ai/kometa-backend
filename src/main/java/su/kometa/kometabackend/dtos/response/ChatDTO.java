package su.kometa.kometabackend.dtos.response;

import lombok.Data;
import su.kometa.kometabackend.models.Chat;

@Data
public class ChatDTO {

    private long id;

    private String title;

    private UserDTO user;

    private ModelDTO model;

    public ChatDTO(Chat chat) {
        this.id = chat.getId();
        this.title = chat.getTitle();
        this.user = new UserDTO(chat.getUser());
        this.model = new ModelDTO(chat.getModel());
    }
}