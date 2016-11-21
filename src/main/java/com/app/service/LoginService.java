package com.app.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by romm on 21.11.16.
 */
public class LoginService {

    public static String getEncodedPassword(String password) throws NoSuchAlgorithmException {
        return new String(Base64.getEncoder().encode(encryption(password)));
    }

    private static byte[] encryption(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        return md.digest();
    }

}
