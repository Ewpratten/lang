package ca.retrylife.lang.exceptions;

import java.io.IOException;

public abstract class LanguageLoadException extends IOException {
    public LanguageLoadException(String message) {
        super(message);
    }
}