package su.kometa.kometabackend.dtos.request;

import lombok.Data;
import su.kometa.kometabackend.models.Model;

@Data
public class ChatCreateDTO {

    private Model model;
}
