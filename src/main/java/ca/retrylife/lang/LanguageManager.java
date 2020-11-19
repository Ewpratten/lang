package ca.retrylife.lang;

import ca.retrylife.lang.exceptions.LanguageNotFoundException;
import ca.retrylife.lang.exceptions.NonexistentLanguageFolderException;

/**
 * Main interface for language queries
 */
public class LanguageManager {

    public static final String ERROR_NO_TRANSLATION = "NO_TRANSLATION";

    private static LanguageManager GLOBAL_MANAGER = null;

    /**
     * Get the global instance
     * 
     * @return Global instance
     */
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

    /**
     * Create a LanguageManager
     * 
     * @param language Language to use
     * @throws LanguageNotFoundException
     * @throws NonexistentLanguageFolderException
     */
    public LanguageManager(Language language) throws LanguageNotFoundException, NonexistentLanguageFolderException {
        setLanguage(language);
    }

    /**
     * Create a LanguageManager
     * 
     * @param language Language to use
     * @param fallback Fallback language
     * @throws LanguageNotFoundException
     * @throws NonexistentLanguageFolderException
     */
    public LanguageManager(Language language, Language fallback)
            throws LanguageNotFoundException, NonexistentLanguageFolderException {
        setLanguage(language);
        setFallbackLanguage(language);
    }

    /**
     * Set the current language
     * 
     * @param language Language
     * @throws LanguageNotFoundException
     * @throws NonexistentLanguageFolderException
     */
    public void setLanguage(Language language) throws LanguageNotFoundException, NonexistentLanguageFolderException {

        // Unload current language
        if (primaryLanguage != null && primaryLanguage.isLoaded()) {
            primaryLanguage.unload();
        }

        // Swap in, and load new language
        primaryLanguage = language;
        primaryLanguage.load();
    }

    /**
     * Set the fallback language
     * 
     * @param language Language
     * @throws LanguageNotFoundException
     * @throws NonexistentLanguageFolderException
     */
    public void setFallbackLanguage(Language language)
            throws LanguageNotFoundException, NonexistentLanguageFolderException {

        // Unload current language
        if (fallbackLanguage != null && fallbackLanguage.isLoaded()) {
            fallbackLanguage.unload();
        }

        // Swap in, and load new language
        fallbackLanguage = language;
        fallbackLanguage.load();
    }

    /**
     * Check if a language has been set
     * 
     * @return Has language?
     */
    public boolean hasLanguage() {
        return primaryLanguage != null;
    }

    /**
     * Check if a fallback language has been set
     * 
     * @return Has fallback language?
     */
    public boolean hasFallbackLanguage() {
        return fallbackLanguage != null;
    }

    /**
     * Check if a query must be run on the fallback language
     * 
     * @param key Query key
     * @return Needs fallback?
     */
    public boolean useFallbackForQuery(String key) {
        return !hasLanguage() || primaryLanguage.query(key) == null;
    }

    /**
     * Query for a key
     * 
     * @param key Key
     * @return Result
     */
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

        return ERROR_NO_TRANSLATION;
    }

}