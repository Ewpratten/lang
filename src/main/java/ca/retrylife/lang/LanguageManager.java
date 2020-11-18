package ca.retrylife.lang;

import ca.retrylife.lang.exceptions.LanguageNotFoundException;
import ca.retrylife.lang.exceptions.NonexistantLanguageFolderException;

public class LanguageManager {

    private static LanguageManager GLOBAL_MANAGER = null;

    public LanguageManager getGlobalManager() {
        if (GLOBAL_MANAGER == null) {
            GLOBAL_MANAGER = new LanguageManager();
        }
        return GLOBAL_MANAGER;
    }

    // Languages
    private Language primaryLanguage = null;
    private Language fallbackLanguage = null;

    public LanguageManager() {

    }

    public LanguageManager(Language language) throws LanguageNotFoundException, NonexistantLanguageFolderException {
        setLanguage(language);
    }

    public LanguageManager(Language language, Language fallback)
            throws LanguageNotFoundException, NonexistantLanguageFolderException {
        setLanguage(language);
        setFallbackLanguage(language);
    }

    public void setLanguage(Language language) throws LanguageNotFoundException, NonexistantLanguageFolderException {

        // Unload current language
        if (primaryLanguage != null && primaryLanguage.isLoaded()) {
            primaryLanguage.unload();
        }

        // Swap in, and load new language
        primaryLanguage = language;
        primaryLanguage.load();
    }

    public void setFallbackLanguage(Language language)
            throws LanguageNotFoundException, NonexistantLanguageFolderException {

        // Unload current language
        if (fallbackLanguage != null && fallbackLanguage.isLoaded()) {
            fallbackLanguage.unload();
        }

        // Swap in, and load new language
        fallbackLanguage = language;
        fallbackLanguage.load();
    }

    public boolean hasLanguage() {
        return primaryLanguage != null;
    }

    public boolean hasFallbackLanguage() {
        return fallbackLanguage != null;
    }

    public boolean useFallbackForQuery(String key) {
        return !hasLanguage() || primaryLanguage.query(key) == null;
    }

    public String query(String key) {

        // Query the correct language
        String result = null;
        if (hasLanguage()) {
            result = primaryLanguage.query(key);
        }
        if (result == null && hasFallbackLanguage()) {
            result = fallbackLanguage.query(key);
        }

        if (result != null) {
            return result;
        }

        return "NO_TRANSLATION";
    }

}