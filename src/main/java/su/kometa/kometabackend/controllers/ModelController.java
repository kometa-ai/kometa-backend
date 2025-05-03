package su.kometa.kometabackend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import su.kometa.kometabackend.constants.RoutesConstants;
import su.kometa.kometabackend.dtos.request.ModelEditDTO;
import su.kometa.kometabackend.dtos.response.ModelDTO;
import su.kometa.kometabackend.exceptions.ModelNotFoundException;
import su.kometa.kometabackend.services.ModelService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = RoutesConstants.MODELS_ROUTE, produces = "application/json")
public class ModelController {

    final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/")
    public List<ModelDTO> getAll() throws ModelNotFoundException {
        return modelService.getAll().stream()
                .map(ModelDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/provider/{provider}")
    public List<ModelDTO> getByProvider(@PathVariable String provider) throws ModelNotFoundException {
        return modelService.getAllByProvider(provider).stream()
                .map(ModelDTO::new)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public ModelDTO edit(@PathVariable long id, @RequestBody ModelEditDTO body) throws ModelNotFoundException {
        return new ModelDTO(modelService.edit(id, body));
    }
}