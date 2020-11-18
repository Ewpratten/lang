package ca.retrylife.lang;

import javax.annotation.Nullable;

import ca.retrylife.lang.exceptions.LanguageNotFoundException;
import ca.retrylife.lang.exceptions.NonexistantLanguageFolderException;

public class Language {

    private boolean isLoaded = false;

    public Language(String name) {

    }

    public void load() throws LanguageNotFoundException, NonexistantLanguageFolderException {
        isLoaded = true;
    }

    public void unload() {
        isLoaded = false;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public @Nullable String query(String key) {
        boolean needsToUnload = false;

        // If this is not already loaded, temporarily load the language for this query
        if (!isLoaded) {
            try {
                load();
            } catch (LanguageNotFoundException | NonexistantLanguageFolderException e) {
                return null;
            }
            needsToUnload = true;
        }

        // Unload if this query caused the language to be temp loaded
        if (needsToUnload) {
            unload();
        }
        return null;
    }
}