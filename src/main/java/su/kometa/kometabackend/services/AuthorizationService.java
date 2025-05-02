package su.kometa.kometabackend.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.dtos.request.LoginDTO;
import su.kometa.kometabackend.dtos.request.SignUpDTO;
import su.kometa.kometabackend.exceptions.NeedToAuthorizeException;
import su.kometa.kometabackend.exceptions.UserNotFoundException;
import su.kometa.kometabackend.models.User;

@Service
public class AuthorizationService {

    private final UserService userService;

    private final BCryptService bCryptService;

    private final JWTService jwtService;

    @Autowired
    public AuthorizationService(JWTService jwtService, UserService userService, BCryptService bCryptService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.bCryptService = bCryptService;
    }

    public User signUp(SignUpDTO body) {
        String username = body.getUsername();
        String password = body.getPassword();

        String passwordHash = bCryptService.hashPassword(password);

        return new User(username, passwordHash);
    }

    public User authUser(String accessToken) {
        try {
            return userService.getById(jwtService.validate(accessToken));
        } catch(UserNotFoundException e) {
            throw new NeedToAuthorizeException();
        }
    }

    public String login(@Valid LoginDTO body) {
        String username = body.getUsername();
        String password = body.getPassword();
        User user = userService.getByUsername(username);

        jwtService.validate(password);

        return jwtService.generate(user.getId(), password);
    }
}
