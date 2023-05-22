package us.obviously.itmo.prog.server.database.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD2Secure extends SecureControl{
    private String hash;
    @Override
    public String getHashCode(String args) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] bytes = md.digest(args.getBytes(StandardCharsets.UTF_8));
            hash = new String(bytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
        }
        return hash;
    }
}
