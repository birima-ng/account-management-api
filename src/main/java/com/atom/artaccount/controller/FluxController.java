package com.atom.artaccount.controller;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/flux")
public class FluxController {

    @Value("${app.private-key-path}")
    private String privateKeyPath;

    private RSAPrivateKey privateKey;

    private final ObjectMapper mapper = new ObjectMapper();
    String pem1 ="valeu";

    // ===============================
    // Endpoint principal Flow
    // ===============================
    @PostMapping("/inscription")
    public ResponseEntity<String> recevoirFlux(@RequestBody Map<String, String> payload) {

        try {

            // =========================
            // 1️⃣ Decode Base64
            // =========================
            byte[] encryptedFlowData =
                    Base64.getDecoder().decode(payload.get("encrypted_flow_data"));

            byte[] encryptedAesKey =
                    Base64.getDecoder().decode(payload.get("encrypted_aes_key"));

            byte[] iv =
                    Base64.getDecoder().decode(payload.get("initial_vector"));

            // =========================
            // 2️⃣ RSA decrypt AES key
            // =========================
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

            rsaCipher.init(
                    Cipher.DECRYPT_MODE,
                    getPrivateKey(),
                    new OAEPParameterSpec(
                            "SHA-256",
                            "MGF1",
                            MGF1ParameterSpec.SHA256,
                            PSource.PSpecified.DEFAULT
                    )
            );

            byte[] aesKey = rsaCipher.doFinal(encryptedAesKey);

            // =========================
            // 3️⃣ AES-GCM decrypt payload
            // =========================
            GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);

            Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
            aesCipher.init(
                    Cipher.DECRYPT_MODE,
                    new SecretKeySpec(aesKey, "AES"),
                    gcmSpec
            );

            byte[] decryptedBytes = aesCipher.doFinal(encryptedFlowData);

            String clearJson = new String(decryptedBytes, StandardCharsets.UTF_8);

            System.out.println("✅ JSON déchiffré = " + clearJson);

            // =========================
            // 4️⃣ Construire réponse
            // =========================
            String clearResponse =
                    "{\"screen\":\"QUESTION_ONE\",\"data\":{\"message\":\"Demande reçue\"}}";

            // IMPORTANT : flip IV comme dans l'exemple officiel
            byte[] flippedIv = flipIv(iv);

            GCMParameterSpec responseSpec = new GCMParameterSpec(128, flippedIv);

            aesCipher.init(
                    Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(aesKey, "AES"),
                    responseSpec
            );

            byte[] encryptedResponse =
                    aesCipher.doFinal(clearResponse.getBytes(StandardCharsets.UTF_8));

            String base64Response =
                    Base64.getEncoder().encodeToString(encryptedResponse);

            return ResponseEntity.ok(base64Response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Erreur traitement Flow : " + e.getMessage()+" "+pem1);
        }
    }

    private byte[] flipIv(byte[] iv) {
        byte[] result = new byte[iv.length];
        for (int i = 0; i < iv.length; i++) {
            result[i] = (byte) (iv[i] ^ 0xFF);
        }
        return result;
    }
    
    private String clean(String value) {
        if (value == null) return null;
        return value.replaceAll("\\s", "");
    }

    private byte[] decodeBase64(String value) {
        try {
            return Base64.getDecoder().decode(value);
        } catch (IllegalArgumentException e) {
            return Base64.getUrlDecoder().decode(value);
        }
    }
    
    private byte[] safeBase64Decode(String value) {

        String cleaned = value
                .replaceAll("\\s", "")
                .replace("\n", "")
                .replace("\r", "");

        try {
            return Base64.getUrlDecoder().decode(cleaned);
        } catch (IllegalArgumentException e) {
            return Base64.getDecoder().decode(cleaned);
        }
    }
    // ===============================
    // Lazy load clé privée
    // ===============================
    private RSAPrivateKey getPrivateKey() throws Exception {

        if (privateKey != null) {
            return privateKey;
        }

        String pem = Files.readString(Path.of(privateKeyPath));

        // 🔥 Nettoyage strict
        pem = pem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");   // enlève TOUT espaces, \n, \r
        pem1 = pem;
        byte[] keyBytes = Base64.getDecoder().decode(pem);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        privateKey = (RSAPrivateKey) kf.generatePrivate(spec);

        return privateKey;
    }



    // ===============================
    // Décrypt Flow data AES
    // ===============================
    private String decryptFlowData(String encryptedDataB64,
                                   SecretKey aesKey,
                                   String ivB64) throws Exception {

        Cipher cipher =
                Cipher.getInstance("AES/CBC/PKCS5Padding");

        IvParameterSpec iv =
                new IvParameterSpec(Base64.getDecoder().decode(ivB64));

        cipher.init(Cipher.DECRYPT_MODE, aesKey, iv);

        byte[] decrypted =
                cipher.doFinal(Base64.getDecoder().decode(encryptedDataB64));

        return new String(decrypted, StandardCharsets.UTF_8);
    }

    // ===============================
    // Encrypt réponse vers Meta
    // ===============================
    private Map<String, String> encryptResponse(
            Map<String, Object> response,
            SecretKey aesKey) throws Exception {

        String json = mapper.writeValueAsString(response);

        Cipher cipher =
                Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] ivBytes = new byte[16];
        new SecureRandom().nextBytes(ivBytes);

        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        cipher.init(Cipher.ENCRYPT_MODE, aesKey, iv);

        byte[] encrypted =
                cipher.doFinal(json.getBytes(StandardCharsets.UTF_8));

        return Map.of(
                "encrypted_flow_data",
                Base64.getEncoder().encodeToString(encrypted),
                "initial_vector",
                Base64.getEncoder().encodeToString(ivBytes)
        );
    }
}