package ca.retrylife.lang.exceptions;

public class NonexistentLanguageFolderException extends LanguageLoadException {
    public NonexistentLanguageFolderException() {
        super("resources/lang directory does not exist");
    }
}