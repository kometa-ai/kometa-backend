package su.kometa.kometabackend.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BCryptService {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
