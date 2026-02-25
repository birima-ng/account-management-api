package com.atom.artaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/flux")
public class FluxController {

    @Value("${app.private-key-path}")
    private String privateKeyPath;

    private RSAPrivateKey privateKey;

    private final ObjectMapper mapper = new ObjectMapper();

    // ===============================
    // Endpoint principal Flow
    // ===============================
    @PostMapping("/inscription")
    public ResponseEntity<String> recevoirFlux(@RequestBody Map<String, String> payload) {

        try {

            // =========================
            // 1️⃣ Récupération et nettoyage
            // =========================
            String encryptedAesKey = clean(payload.get("encrypted_aes_key"));
            String encryptedFlowData = clean(payload.get("encrypted_flow_data"));
            String ivB64 = clean(payload.get("initial_vector"));

            if (encryptedAesKey == null || encryptedFlowData == null || ivB64 == null) {
                return ResponseEntity.ok("{\"status\":\"error\",\"message\":\"Payload invalide\"}");
            }

            // =========================
            // 2️⃣ Décrypt AES Key (RSA)
            // =========================
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            rsaCipher.init(Cipher.DECRYPT_MODE, getPrivateKey());

            byte[] aesKeyBytes = rsaCipher.doFinal(decodeBase64(encryptedAesKey));
            SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

            // =========================
            // 3️⃣ Décrypt Flow Data (AES)
            // =========================
            byte[] ivBytes = decodeBase64(ivB64);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            aesCipher.init(Cipher.DECRYPT_MODE, aesKey, iv);

            byte[] decryptedBytes = aesCipher.doFinal(decodeBase64(encryptedFlowData));
            String decryptedJson = new String(decryptedBytes, StandardCharsets.UTF_8);

            System.out.println("📥 JSON reçu déchiffré : " + decryptedJson);

            Map<String, Object> flowData =
                    new ObjectMapper().readValue(decryptedJson, Map.class);

            // =========================
            // 4️⃣ Health Check
            // =========================
            if ("ping".equals(flowData.get("action"))) {
                return ResponseEntity.ok("{\"status\":\"active\"}");
            }

            // =========================
            // 5️⃣ Traitement formulaire
            // =========================
            String prenom = (String) flowData.getOrDefault("prenom", "Utilisateur");

            Map<String, Object> responsePayload = Map.of(
                    "screen", "QUESTION_ONE",
                    "data", Map.of(
                            "message", "Merci " + prenom + ", votre demande est reçue."
                    )
            );

            String responseJson = new ObjectMapper().writeValueAsString(responsePayload);

            // =========================
            // 6️⃣ Encrypt réponse (AES)
            // =========================
            byte[] newIvBytes = new byte[16];
            new SecureRandom().nextBytes(newIvBytes);

            IvParameterSpec newIv = new IvParameterSpec(newIvBytes);
            aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, newIv);

            byte[] encryptedResponse =
                    aesCipher.doFinal(responseJson.getBytes(StandardCharsets.UTF_8));

            String encryptedBase64 =
                    Base64.getEncoder().encodeToString(encryptedResponse);

            String ivBase64 =
                    Base64.getEncoder().encodeToString(newIvBytes);

            // =========================
            // 7️⃣ Retour HTTP 200
            // =========================
            String finalResponse = String.format(
                    "{\"encrypted_flow_data\":\"%s\",\"initial_vector\":\"%s\"}",
                    encryptedBase64,
                    ivBase64
            );

            return ResponseEntity.ok(finalResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("{\"status\":\"error\",\"message\":\"Erreur traitement Flow"+e.getMessage()+" type "+e.getLocalizedMessage()+" \"}");
        }
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

        byte[] keyBytes = Base64.getDecoder().decode(pem);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        privateKey = (RSAPrivateKey) kf.generatePrivate(spec);

        return privateKey;
    }

    // ===============================
    // Décrypt AES key via RSA
    // ===============================
    private SecretKey decryptAesKey(String encryptedKeyB64) throws Exception {

        Cipher cipher =
                Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());

        byte[] aesKeyBytes =
                cipher.doFinal(Base64.getDecoder().decode(encryptedKeyB64));

        return new SecretKeySpec(aesKeyBytes, "AES");
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