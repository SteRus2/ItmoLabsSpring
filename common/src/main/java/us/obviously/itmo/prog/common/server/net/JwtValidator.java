package us.obviously.itmo.prog.common.server.net;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import us.obviously.itmo.prog.common.server.exceptions.InvalidTokenException;

import java.util.Collections;
import java.util.List;

import static us.obviously.itmo.prog.common.server.net.Server.logger;

public class JwtValidator {

    private static final List<String> allowedIsses = Collections.singletonList("https://keycloak.quadmeup.com/auth/realms/Realm");

    private String getKeycloakCertificateUrl(DecodedJWT token) {
        return token.getIssuer() + "/protocol/openid-connect/certs";
    }

    /**
     * Validate a JWT token
     *
     * @param token
     * @return decoded token
     */
    public DecodedJWT validate(String token) throws InvalidTokenException {
        try {
            final DecodedJWT jwt = JWT.decode(token);

//            if (!allowedIsses.contains(jwt.getIssuer())) {
//                throw new InvalidTokenException(String.format("Unknown Issuer %s", jwt.getIssuer()));
//            }

            Algorithm algorithm = Algorithm.HMAC256(JwtGenerator.key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(jwt.getIssuer())
                    .build();

            verifier.verify(token);
            return jwt;

        } catch (Exception e) {
            logger.warning("Failed to validate JWT: " + e.getMessage());
            throw new InvalidTokenException("JWT validation failed: " + e.getMessage());
        }
    }
}