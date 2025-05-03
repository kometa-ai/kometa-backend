package su.kometa.kometabackend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import su.kometa.kometabackend.configs.CommonConfig;
import su.kometa.kometabackend.constants.RoutesConstants;
import su.kometa.kometabackend.dtos.response.InfoDTO;

@Slf4j
@RestController
@RequestMapping(value = RoutesConstants.AUTH_ROUTE, produces = "application/json")
public class CommonController {

    private final CommonConfig commonConfig;

    public CommonController(CommonConfig commonConfig) {
        this.commonConfig = commonConfig;
    }

    @GetMapping("/info")
    public InfoDTO getInfo() {
        return new InfoDTO(commonConfig);
    }
}
