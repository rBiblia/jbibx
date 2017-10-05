package net.avensome.dev.bibx.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AboutTest {
    @Test
    public void replacesNullsWithEmptyStrings() {
        About about = new About(null, false, null, null, null, null);
        assertEquals("", about.getLanguage());
        assertEquals("", about.getName());
        assertEquals("", about.getDescription());
        assertEquals("", about.getShortName());
        assertEquals("", about.getDate());
    }
}
