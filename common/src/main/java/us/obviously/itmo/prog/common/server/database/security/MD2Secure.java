package us.obviously.itmo.prog.common.server.database.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD2Secure extends SecureControl {
    private String hash;

    @Override
    public String getHashCode(String args) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");
            byte[] inputBytes = args.getBytes();
            messageDigest.update(inputBytes);
            byte[] hashBytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ignored) {
        }
        return hash;
    }
}
