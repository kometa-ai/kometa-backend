package su.kometa.kometabackend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import su.kometa.kometabackend.constants.RoutesConstants;
import su.kometa.kometabackend.dtos.response.ModelDTO;
import su.kometa.kometabackend.services.ModelService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = RoutesConstants.MODELS_ROUTES, produces = "application/json")
public class ModelController {

    final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/")
    public List<ModelDTO> getAll() {
        return modelService.getAll().stream()
                .map(ModelDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/provider/{provider}")
    public List<ModelDTO> getByProvider(@PathVariable String provider) {
        return modelService.getAllByProvider(provider).stream()
                .map(ModelDTO::new)
                .collect(Collectors.toList());
    }
}
