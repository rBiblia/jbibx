package net.avensome.dev.bibx.model;

import org.junit.Test;

import java.util.Arrays;

public class BibleTest {
    @Test(expected = SemanticException.class)
    public void rejectsDuplicateBooks() {
        Book book1 = new Book(BookID.OT_GEN);
        Book book2 = new Book(BookID.OT_GEN);
        About about = new About(null, false, null, null, null, null);
        new Bible(about, Arrays.asList(book1, book2));
    }
}
