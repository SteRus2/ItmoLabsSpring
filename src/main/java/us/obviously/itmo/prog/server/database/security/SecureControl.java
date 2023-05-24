package us.obviously.itmo.prog.server.database.security;

import java.security.SecureRandom;

public abstract class SecureControl {

    private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789<>?:@{!$%^&*()_+£$";
    private final SecureRandom secureRandom = new SecureRandom();
    private final int SALT_CAPACITY = 10;


    public String getSalt(){
        var stringBuilder = new StringBuilder(SALT_CAPACITY);
        for (int i = 0; i < SALT_CAPACITY; i++){
            stringBuilder.append(LETTERS.charAt(secureRandom.nextInt(LETTERS.length())));
        }
        return stringBuilder.toString();
    }
    public abstract String getHashCode(String args);
}
