package su.kometa.kometabackend.services;

import org.springframework.stereotype.Service;
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
}
