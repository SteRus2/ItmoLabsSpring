package us.obviously.itmo.prog.server.database.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD2Secure extends SecureControl{
    private String hash;
    private MessageDigest messageDigest;
    @Override
    public String getHashCode(String args) {
        try {
            messageDigest = MessageDigest.getInstance("MD2");
            byte[] inputBytes = args.getBytes();
            messageDigest.update(inputBytes);
            byte[] hashBytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return hash;
    }
}
