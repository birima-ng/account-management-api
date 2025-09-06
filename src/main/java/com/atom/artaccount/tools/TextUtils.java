package com.atom.artaccount.tools;

import java.text.Normalizer;

public class TextUtils {
    public static String normalize(String input) {
        if (input == null) return null;
        // Décomposer les accents puis supprimer les diacritiques
        if (input == null) return null;
        return Normalizer.normalize(input, Normalizer.Form.NFC).toLowerCase();
    }
}
