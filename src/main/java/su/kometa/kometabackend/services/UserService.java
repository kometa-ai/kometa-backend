package su.kometa.kometabackend.services;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.dtos.request.UserEditDTO;
import su.kometa.kometabackend.exceptions.UserNotFoundException;
import su.kometa.kometabackend.models.User;
import su.kometa.kometabackend.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User edit(User user, @Valid UserEditDTO body) {
        user.setUsername(body.getUsername());
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
