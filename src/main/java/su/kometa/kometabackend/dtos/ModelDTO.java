package su.kometa.kometabackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import su.kometa.kometabackend.models.Model;

@Data
public class ModelDTO {

    private long id;

    private String name;

    public ModelDTO(Model model) {
        this.id = model.getId();
        this.name = model.getName();
    }
}
