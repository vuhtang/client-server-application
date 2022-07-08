package gui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Helper class for more convenient labels translation.
 */
public class Translator {
    private static final Map<String, Locale> locales = new HashMap<>();
    static {
        locales.put("English", new Locale("en"));
        locales.put("Русский", new Locale("ru"));
        locales.put("Română", new Locale("ro"));
        locales.put("Ελληνικά", new Locale("el"));
    }
    private static Locale currentLocale = getLocale("English");

    public static Locale getLocale(String shortName) {
        return locales.get(shortName);
    }

    public static void setLocale(String localeName) {
        currentLocale = getLocale(localeName);
    }

    public static Locale currentLocale() {
        return currentLocale;
    }
}
