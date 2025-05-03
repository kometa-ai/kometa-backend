package su.kometa.kometabackend.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import su.kometa.kometabackend.constants.AttributesConstants;
import su.kometa.kometabackend.constants.RoutesConstants;
import su.kometa.kometabackend.dtos.request.UserEditDTO;
import su.kometa.kometabackend.dtos.response.OkDTO;
import su.kometa.kometabackend.dtos.response.UserDTO;
import su.kometa.kometabackend.models.User;
import su.kometa.kometabackend.services.UserService;

@Slf4j
@RestController
@RequestMapping(value = RoutesConstants.USERS_ROUTE, produces = "application/json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/@me")
    public UserDTO edit(@RequestAttribute(name = AttributesConstants.USER) User user, @Valid @RequestBody UserEditDTO body) {
        return new UserDTO(userService.editUser(user, body));
    }

    @DeleteMapping("/@me")
    public OkDTO delete(@RequestAttribute(name = AttributesConstants.USER) User user) {
        userService.delete(user);
        return new OkDTO();
    }
}
