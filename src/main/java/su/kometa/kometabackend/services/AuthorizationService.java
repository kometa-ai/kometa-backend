package su.kometa.kometabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.configs.CommonConfig;
import su.kometa.kometabackend.dtos.request.LoginDTO;
import su.kometa.kometabackend.dtos.request.SignUpDTO;
import su.kometa.kometabackend.exceptions.NeedToAuthorizeException;
import su.kometa.kometabackend.exceptions.UserNotFoundException;
import su.kometa.kometabackend.exceptions.WrongPasswordException;
import su.kometa.kometabackend.models.User;

@Service
public class AuthorizationService {

    private final UserService userService;

    private final BCryptService bCryptService;

    private final JWTService jwtService;

    private final CommonConfig commonConfig;

    @Autowired
    public AuthorizationService(JWTService jwtService, UserService userService, BCryptService bCryptService, CommonConfig commonConfig) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.bCryptService = bCryptService;
        this.commonConfig = commonConfig;
    }

    public User authUser(String accessToken) throws NeedToAuthorizeException {
        long userId;

        try {
            userId = jwtService.validate(accessToken);
        } catch (Exception e) {
            throw new NeedToAuthorizeException();
        }

        return userService.getById(userId);
    }

    public String signUp(SignUpDTO body) {
        String username = body.getUsername();
        String password = body.getPassword();
        String passwordHash;

        if (commonConfig.isInviteOnly()) return null;
        else passwordHash = bCryptService.getHash(password);

        User user = new User(username, passwordHash);

        userService.create(user);

        return jwtService.generate(user.getId(), passwordHash);
    }

    public String login(LoginDTO body) throws WrongPasswordException {
        String username = body.getUsername();
        String password = body.getPassword();
        User user = userService.getByUsername(username);

        if (bCryptService.verify(password, user.getPassword())) throw new WrongPasswordException();

        return jwtService.generate(user.getId(), password);
    }
}
