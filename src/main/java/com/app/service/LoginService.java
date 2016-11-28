package com.app.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * This class represents utils for encrypting password
 * Created by romm on 21.11.16.
 */
public class LoginService {

    /**
     *
     * @param password - SHA-256 encrypted password
     * @return Base64 encoded password
     * @throws NoSuchAlgorithmException
     */
    public static String getEncodedPassword(String password) throws NoSuchAlgorithmException {
        return new String(Base64.getEncoder().encode(encryption(password)));
    }

    /**
     *
     * @param password - unhashed password
     * @return SHA-256 encrypted password
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encryption(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        return md.digest();
    }

}
