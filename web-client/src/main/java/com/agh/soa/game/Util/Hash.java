package com.agh.soa.game.Util;

import java.security.MessageDigest;
import java.util.Base64;

public class Hash {
    public static void main(String[] args) throws Exception{
        String password = "user1";
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        byte[] passwordBytes = password.getBytes();
        byte[] hash = md.digest(passwordBytes);
        String passwordHash = Base64.getEncoder().encodeToString(hash);
        System.out.println("skrót hasła: "+passwordHash); }
}