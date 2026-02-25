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
    public ResponseEntity<?> recevoirFlux(@RequestBody Map<String, String> payload) {

        try {

            // 1️⃣ Récupération données chiffrées
            String encryptedAesKey = payload.get("encrypted_aes_key");
            String encryptedFlowData = payload.get("encrypted_flow_data");
            String iv = payload.get("initial_vector");

            if (encryptedAesKey == null || encryptedFlowData == null || iv == null) {
                return ResponseEntity.badRequest().body("Payload invalide");
            }

            // 2️⃣ Décrypt AES key
            SecretKey aesKey = decryptAesKey(encryptedAesKey);

            // 3️⃣ Décrypt Flow JSON
            String decryptedJson = decryptFlowData(encryptedFlowData, aesKey, iv);

            System.out.println("📥 Flow déchiffré : " + decryptedJson);

            Map<String, Object> flowData =
                    mapper.readValue(decryptedJson, Map.class);

            // ===============================
            // Health Check Meta
            // ===============================
            if ("ping".equals(flowData.get("action"))) {
                return ResponseEntity.ok(Map.of("status", "active"));
            }

            // ===============================
            // Traitement formulaire
            // ===============================
           /* String nom = (String) flowData.getOrDefault("nom", "Utilisateur");

            Map<String, Object> responsePayload = Map.of(
                    "screen", "SUCCESS",
                    "data", Map.of(
                            "message", "Merci " + nom + ", formulaire validé."
                    )
            );
*/
            Map<String, Object> responsePayload = Map.of(
                    "screen", "SUCCESS",
                    "data", Map.of(
                            "message", "Merci , formulaire validé."
                    )
            );
            // 4️⃣ Encrypt réponse
            Map<String, String> encryptedResponse =
                    encryptResponse(responsePayload, aesKey);

            return ResponseEntity.ok(encryptedResponse);

        } catch (Exception e) {
           // e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Erreur traitement Flow");
        }
        
        
    }

    // ===============================
    // Lazy load clé privée
    // ===============================
    private RSAPrivateKey getPrivateKey() throws Exception {

        if (privateKey != null) {
            return privateKey;
        }

        System.out.println("🔐 Chargement clé depuis : " + privateKeyPath);

        String pem = Files.readString(Path.of(privateKeyPath));

        String content = pem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(content);

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(keyBytes);

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