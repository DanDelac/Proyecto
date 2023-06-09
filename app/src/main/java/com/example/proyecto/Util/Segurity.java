package com.example.proyecto.Util;

import java.util.Arrays;
import java.util.List;

public class Segurity {
    public static boolean verifyPassword(String contraseña) {
        String patron = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return contraseña.matches(patron);
    }
    public static boolean verifyAccount(String account) {
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

}
