package su.kometa.kometabackend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.configs.JWTConfig;
import su.kometa.kometabackend.constants.TokenConstants;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    private final JWTConfig jwtConfig;

    @Autowired
    public JWTService(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generate(long id, String passwordHash) {
        long now = System.currentTimeMillis();
        Date expirationDate = new Date(now + TokenConstants.MONTHLY);

        return Jwts.builder()
                .id(String.valueOf(id))
                .subject(passwordHash)
                .expiration(expirationDate)
                .signWith(getSigningKey())
                .compact();
    }

    public long validate(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);

        return Long.parseLong(claimsJws.getPayload().getId());
    }

    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecret()));
    }
}
