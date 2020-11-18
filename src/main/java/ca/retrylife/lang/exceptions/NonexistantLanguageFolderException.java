package ca.retrylife.lang.exceptions;

public class NonexistantLanguageFolderException extends LanguageLoadException {
    public NonexistantLanguageFolderException() {
        super("resources/lang directory does not exist");
    }
}