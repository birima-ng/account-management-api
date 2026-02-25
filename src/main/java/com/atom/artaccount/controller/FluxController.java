package com.atom.artaccount.controller;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atom.artaccount.dto.ReclamationDTO;
import com.atom.artaccount.model.Reclamation;
import com.atom.artaccount.service.ReclamationService;

import org.springframework.core.io.ClassPathResource;

@RestController
@RequestMapping("/api/flux")
public class FluxController {

    private final RSAPrivateKey privateKey;

    public FluxController() throws Exception {
        // Charger depuis classpath
        ClassPathResource resource = new ClassPathResource("private.pem"); //cles
        String privateKeyPem = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        this.privateKey = loadPrivateKey(privateKeyPem);
    }

    @PostMapping("/inscription")
    public ResponseEntity<Map<String, Object>> recevoirFlux(@RequestBody Map<String, Object> payload) {
        try {
            String encryptedAesKey = (String) payload.get("encrypted_aes_key");
            String encryptedFlowData = (String) payload.get("encrypted_flow_data");
            String initialVector = (String) payload.get("initial_vector");

            SecretKey aesKey = decryptAesKey(encryptedAesKey, privateKey);
            String flowDataJson = decryptFlowData(encryptedFlowData, aesKey, initialVector);

            System.out.println("Données Flow déchiffrées : " + flowDataJson);

            Map<String, Object> response = Map.of("status", "ok");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    // ---------------- Méthodes utilitaires ----------------

    private RSAPrivateKey loadPrivateKey(String pem) throws Exception {
        String privateKeyContent = pem
                .replaceAll("\\n", "")
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(keySpec);
    }

    private SecretKey decryptAesKey(String encryptedAesKeyB64, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] aesKeyBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedAesKeyB64));
        return new SecretKeySpec(aesKeyBytes, "AES");
    }

    private String decryptFlowData(String encryptedDataB64, SecretKey aesKey, String ivB64) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(ivB64));
        cipher.init(Cipher.DECRYPT_MODE, aesKey, iv);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedDataB64));
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}