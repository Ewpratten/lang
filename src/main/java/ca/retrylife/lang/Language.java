package ca.retrylife.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Nullable;

import ca.retrylife.lang.exceptions.LanguageNotFoundException;
import ca.retrylife.lang.exceptions.NonexistentLanguageFolderException;

/**
 * A Language
 */
public class Language {

    // Properties
    private Properties file = null;

    // name
    private final String name;

    /**
     * Create a Language
     * 
     * @param name Language file name
     */
    public Language(String name) {
        this.name = name;
    }

    /**
     * Load
     * 
     * @throws LanguageNotFoundException
     * @throws NonexistentLanguageFolderException
     */
    public void load() throws LanguageNotFoundException, NonexistentLanguageFolderException {

        // Set up properties
        file = new Properties();

        // Load from resources
        InputStream rawInput = getClass().getClassLoader()
                .getResourceAsStream(String.format("lang/%s.lang", this.name));

        if (rawInput == null) {
            throw new NonexistentLanguageFolderException();
        }

        try {
            file.load(rawInput);
        } catch (IOException e) {
            throw new LanguageNotFoundException(this.name);
        }

    }

    /**
     * Unload
     */
    public void unload() {
        file = null;
    }

    /**
     * Check if the language is loaded
     * 
     * @return Is loaded?
     */
    public boolean isLoaded() {
        return file != null;
    }

    /**
     * Query the language file
     * 
     * @param key Key
     * @return Result or null
     */
    public @Nullable String query(String key) {
        boolean needsToUnload = false;

        // If this is not already loaded, temporarily load the language for this query
        if (!isLoaded()) {
            try {
                load();
            } catch (LanguageNotFoundException | NonexistentLanguageFolderException e) {
                return null;
            }
            needsToUnload = true;
        }

        // Run query
        String result = file.getProperty(key, null);

        // Unload if this query caused the language to be temp loaded
        if (needsToUnload) {
            unload();
        }

        return result;
    }
}