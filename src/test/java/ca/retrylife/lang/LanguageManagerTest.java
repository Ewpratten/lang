package ca.retrylife.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.retrylife.lang.exceptions.LanguageNotFoundException;
import ca.retrylife.lang.exceptions.NonexistentLanguageFolderException;

public class LanguageManagerTest {

    @Test
    public void testFetchString() throws LanguageNotFoundException, NonexistentLanguageFolderException {
        
        LanguageManager manager = new LanguageManager(new Language("en_us"));

        // Run query
        assertEquals("Hello, world!", manager.query("test.greeting"));
    }
    
}