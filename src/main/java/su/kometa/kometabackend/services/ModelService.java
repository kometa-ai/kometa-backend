package su.kometa.kometabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.dtos.request.ModelEditDTO;
import su.kometa.kometabackend.exceptions.ModelNotFoundException;
import su.kometa.kometabackend.models.Model;
import su.kometa.kometabackend.repositories.ModelRepository;

import java.util.List;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public Model getById(long id) {
        return modelRepository.findById(id).orElseThrow(ModelNotFoundException::new);
    }

    public Model getByProvider(String provider) {
        return modelRepository.findByProvider(provider).orElseThrow(ModelNotFoundException::new);
    }

    public List<Model> getAllByProvider(String provider) {
        return modelRepository.findAllByProvider(provider);
    }

    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    public Model edit(long id, ModelEditDTO body) {
        Model model = getById(id);
        model.setApiKey(body.getApiKey());
        model.setEnabled(body.isEnabled());
        return modelRepository.save(model);
    }
}
