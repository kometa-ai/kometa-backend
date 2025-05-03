package su.kometa.kometabackend.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BCryptService {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public String getHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean verify(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
