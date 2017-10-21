package net.avensome.dev.jbibx.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AboutTest {
    @Test
    public void replacesNullsWithEmptyStrings() {
        About about = new About(null, false, null, null, null, null, null);
        assertEquals("", about.getLanguage());
        assertEquals("", about.getName());
        assertEquals("", about.getDescription());
        assertEquals("", about.getShortName());
        assertEquals("", about.getDate());
        assertEquals("", about.getAuthor());
    }
}
