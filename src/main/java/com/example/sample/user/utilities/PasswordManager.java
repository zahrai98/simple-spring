package com.example.sample.user.utilities;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {

    public static String hashPassword(String passwordPlaintext) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(passwordPlaintext, salt);

    }

    public static boolean checkPassword(String passwordPlaintext, String storedHash) {
        boolean passwordVerified = false;

        if(null == storedHash || !storedHash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided for comparison"); //Todo correct exp

        passwordVerified = BCrypt.checkpw(passwordPlaintext, storedHash);

        return(passwordVerified);
    }
}
