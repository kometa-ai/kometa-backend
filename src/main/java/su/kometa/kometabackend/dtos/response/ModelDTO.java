package su.kometa.kometabackend.dtos.response;

import lombok.Data;
import su.kometa.kometabackend.models.Model;

@Data
public class ModelDTO {

    private long id;

    private String name;

    private String provider;

    public ModelDTO(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.provider = model.getProvider();
    }
}
