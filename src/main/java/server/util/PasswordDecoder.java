package server.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class PasswordDecoder {
    public static String decodeToSha1(String password){
        return Hashing.sha1().hashString(password, StandardCharsets.UTF_8).toString();
    }

    private PasswordDecoder() {
    }
}
