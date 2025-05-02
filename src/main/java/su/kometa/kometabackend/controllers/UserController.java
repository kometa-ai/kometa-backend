package su.kometa.kometabackend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.kometa.kometabackend.constants.AttributesConstants;
import su.kometa.kometabackend.constants.RoutesConstants;
import su.kometa.kometabackend.dtos.response.OkDTO;
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

    @DeleteMapping("/@me")
    public OkDTO deleteUser(@RequestAttribute(name = AttributesConstants.USER) User user) {
        userService.delete(user);
        return new OkDTO();
    }
}
