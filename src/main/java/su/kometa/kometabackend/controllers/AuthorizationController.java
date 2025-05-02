package su.kometa.kometabackend.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import su.kometa.kometabackend.constants.RoutesConstants;
import su.kometa.kometabackend.dtos.request.SignUpDTO;
import su.kometa.kometabackend.dtos.response.OkDTO;
import su.kometa.kometabackend.services.AuthorizationService;
import su.kometa.kometabackend.dtos.response.UserDTO;

@Slf4j
@RestController
@RequestMapping(value = RoutesConstants.AUTH_ROUTE, produces = "application/json")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/signup")
    public UserDTO signUp(@Valid @RequestBody SignUpDTO body) {
        return new UserDTO(authorizationService.signUp(body));
    }
}
