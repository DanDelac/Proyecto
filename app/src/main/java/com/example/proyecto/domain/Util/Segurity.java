package com.example.proyecto.domain.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Segurity {
    public static boolean verifyPassword(String contraseña) {
        String patron = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*/!¡¿?-])(?=\\S+$).{8,}$";
        return contraseña.matches(patron);
    }
    private static boolean isSimplePassword(String contraseña) {
        List<String> simplePasswords = Arrays.asList(
                "password", "123456", "123456789", "12345678", "12345",
                "1234567", "1234567", "qwerty", "abc123", "111111",
                "123123", "admin", "letmein", "welcome", "monkey",
                "password1", "123qwe", "1234", "sunshine", "qwertyuiop",
                "princess", "admin123", "passw0rd", "superman", "iloveyou"
        );

        return simplePasswords.contains(contraseña.toLowerCase());
    }
    public static boolean verifyAccount(String account) {
        if (account.length() < 10) {
            return false;
        }
        List<String> securityAccounts = Arrays.asList
                ("admin", "user", "root", "administrador", "usuario123", "contrasena123"
                        , "admin123","admin0","admin1","admin2","admin3","admin4","admin5",
                        "password123", "testuser", "guest123", "12345678", "qwerty123", "welcome1",
                        "userpass", "user0", "user1", "user2", "user3", "user4", "user5",
                        "password1", "adminadmin", "1234abcd", "guestuser", "demo123",
                        "access123", "testuser1", "111111", "123abc", "defaultuser", "pass1234", "simpleuser",
                        "welcome123", "userpass123", "tempuser", "demoaccount", "987654321", "qwertyuiop", "test12345");

        return securityAccounts.contains(account.toLowerCase());
    }
    public static String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
