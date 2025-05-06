package org.web.task_application.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HmacUtil {
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String SECRET_KEY = "ваш секретный ключ";

    public static String calculateHmac(Map<String, String> params) throws NoSuchAlgorithmException, InvalidKeyException {
        Map<String, String> sortedParams = new TreeMap<>(params);

        String paramString = sortedParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        Mac sha256Hmac = Mac.getInstance(HMAC_SHA256);
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
        sha256Hmac.init(secretKey);

        byte[] hmacBytes = sha256Hmac.doFinal(paramString.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hmacBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
