package us.obviously.itmo.prog.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import us.obviously.itmo.prog.common.server.exceptions.JwtTokenExpiredException;

import java.time.Instant;

public class JwtClientValidator {

    /**
     * Validate a JWT token
     *
     * @param token
     * @return decoded token
     */
    public DecodedJWT validate(String token) throws JwtTokenExpiredException {
        final DecodedJWT jwt = JWT.decode(token);

        var expiresAt = jwt.getExpiresAtAsInstant();
        if (Instant.now().isAfter(expiresAt)) {
            throw new JwtTokenExpiredException("Токен спёкся");
        }
        return jwt;
    }
}