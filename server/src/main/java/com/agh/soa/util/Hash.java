package com.agh.soa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {

    public static String hash(String passwd) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        byte[] passwordBytes = passwd.getBytes();
        byte[] hash = md.digest(passwordBytes);
        return Base64.getEncoder().encodeToString(hash);
        }
}