package com.atom.artaccount.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.tools.SslUtils;
import com.atom.artaccount.tools.TextUtils;

@RestController
@RequestMapping("/api")
public class SearchWords {

    @GetMapping("/pdf/contains")
    public ResponseEntity<Boolean> checkWordsInPdf(
            @RequestParam String url,
            @RequestParam List<String> words) {
        try {
        	SslUtils.disableSslVerification();
            // Télécharger le PDF
            InputStream input = new URL(url).openStream();

            // Lire le contenu du PDF
            PDDocument document = PDDocument.load(input);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();

            // 👉 Afficher le texte du PDF dans la console
            System.out.println("=== TEXTE DU PDF EXTRAIT ===");
            System.out.println(text);
            System.out.println("============================");
            String normalizedText = TextUtils.normalize(text);
            
            System.out.println("=== TEXTE DU PDF EXTRAIT === normalizedText");
            System.out.println(normalizedText);
            System.out.println("============================");
            // Vérifier si tous les mots sont présents
            boolean allFound = words.stream()
                    .allMatch(word -> normalizedText.contains(TextUtils.normalize(word)));

            return ResponseEntity.ok(allFound);

        } catch (Exception e) {
            e.printStackTrace(); // afficher l'erreur en console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(false);
        }
    }
}
