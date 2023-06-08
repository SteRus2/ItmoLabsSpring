package us.obviously.itmo.prog.server.net;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class JwtGenerator {

    static final String key = "sfS%#$fesf";
    private static final int LIVE_TIME = 300;
    private final KeyPairGenerator keyPairGenerator;
    private final KeyPair keyPair;

    public JwtGenerator() throws NoSuchAlgorithmException {
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    public String generateJwt(Map<String, String> payload) throws Exception {
        JWTCreator.Builder tokenBuilder = JWT.create()
//                .withIssuer("https://keycloak.quadmeup.com/auth/realms/Realm/")
                .withIssuer("Server")
                .withClaim("jti", UUID.randomUUID().toString())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(LIVE_TIME)))
                .withIssuedAt(Date.from(Instant.now()));

        payload.forEach(tokenBuilder::withClaim);

        return tokenBuilder.sign(Algorithm.HMAC256(key));
//        return tokenBuilder.sign(Algorithm.RSA256(((RSAPublicKey) keyPair.getPublic()), ((RSAPrivateKey) keyPair.getPrivate())));


//        JwkProvider provider = new JwkProviderBuilder("https://samples.auth0.com/")
//                .cached(10, 24, TimeUnit.HOURS)
//                .rateLimited(10, 1, TimeUnit.MINUTES)
//                .build();
//        final RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); // private key
//        final String privateKeyId = keyPair.getPrivate(); // private key ID
//
//        RSAKeyProvider keyProvider = new RSAKeyProvider() {
//            @Override
//            public RSAPublicKey getPublicKeyById(String kid) {
//                return (RSAPublicKey) jwkProvider.get(kid).getPublicKey();
//            }
//
//            @Override
//            public RSAPrivateKey getPrivateKey() {
//                // return the private key used
//                return rsaPrivateKey;
//            }
//
//            @Override
//            public String getPrivateKeyId() {
//                return rsaPrivateKeyId;
//            }
//        };
//
//        Algorithm algorithm = Algorithm.RSA256(keyProvider);
//
//        return tokenBuilder.sign(algorithm);
    }
}
