package ca.retrylife.lang.exceptions;

public class LanguageNotFoundException extends LanguageLoadException {
    public LanguageNotFoundException(String name) {
        super(String.format("Language not found: %s", name));
    }
}