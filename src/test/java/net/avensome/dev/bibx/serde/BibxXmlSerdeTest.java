package net.avensome.dev.bibx.serde;

import net.avensome.dev.bibx.model.*;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BibxXmlSerdeTest {
    @Test
    public void serializesXml() {
        About about = new About("pl", true, "Biblia Testowa", "Lorem ipsum dolor sit amet", "BT", "2017");

        Map<Integer, String> verses = new HashMap<>();
        verses.put(2, "Verse 2");
        verses.put(1, "Verse 1");

        Chapter chapter1 = new Chapter(1, verses);
        Chapter chapter2 = new Chapter(2, Arrays.asList("Lorem ipsum", "dolor sit amet"));

        Book book1 = new Book(BookID.OT_GEN, Arrays.asList(chapter2, chapter1));
        Book book2 = new Book(BookID.OT_EXO);
        List<Book> books = Arrays.asList(book1, book2);

        Bible bible = new Bible(about, books);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        new BibxXmlSerde().serialize(bible, outputStream);
        String actualXml = new String(outputStream.toByteArray());

        String expectedXml = getResource("exampleXml1");
        assertEquals(expectedXml, normalize(actualXml));
    }

    @Test
    public void deserializesXml() {
        InputStream xml = getResourceStream("exampleXml1");
        Bible bible = new BibxXmlSerde().deserialize(xml);

        About about = bible.getAbout();
        assertEquals("pl", about.getLanguage());
        assertTrue(about.getAuthorized());
        assertEquals("Biblia Testowa", about.getName());
        assertEquals("Lorem ipsum dolor sit amet", about.getDescription());
        assertEquals("BT", about.getShortName());
        assertEquals("2017", about.getDate());

        List<Book> books = bible.getBooks();
        assertEquals(2, books.size());

        Book genesis = books.get(0);  // FIXME books should be gettable by IDs
        assertEquals(2, genesis.getOrderedChapters().size());
        assertEquals(Arrays.asList("Verse 1", "Verse 2"), genesis.getChapter(1).getOrderedVerses());
        assertEquals(Arrays.asList("Lorem ipsum", "dolor sit amet"), genesis.getChapter(2).getOrderedVerses());

        Book exodus = books.get(1);
        assertEquals(0, exodus.getOrderedChapters().size());
    }

    @Test
    public void deserializesEmptyAbout() {
        InputStream xml = getResourceStream("emptyAbout");
        Bible bible = new BibxXmlSerde().deserialize(xml);

        About about = bible.getAbout();
        assertEquals("", about.getLanguage());
        assertFalse(about.getAuthorized());
        assertEquals("", about.getName());
        assertEquals("", about.getDescription());
        assertEquals("", about.getShortName());
        assertEquals("", about.getDate());
    }

    @Test
    public void deserializesCollapsedAboutTags() {
        InputStream xml = getResourceStream("collapsedAbout");
        Bible bible = new BibxXmlSerde().deserialize(xml);

        About about = bible.getAbout();
        assertEquals("", about.getLanguage());
        assertFalse(about.getAuthorized());
        assertEquals("", about.getName());
        assertEquals("", about.getDescription());
        assertEquals("", about.getShortName());
        assertEquals("", about.getDate());
    }

    @Test
    public void omitsEmptyVerses() {
        InputStream xml = getResourceStream("emptyVerse");
        Bible bible = new BibxXmlSerde().deserialize(xml);

        Book book = bible.getBooks().get(0);
        Chapter chapter = book.getOrderedChapters().get(0);
        assertEquals(0, chapter.getVerses().size());
        assertEquals(0, chapter.getOrderedVerses().size());
    }

    private String getResource(String name) {
        try {
            Path path = Paths.get("src/test/resources", name + ".xml");
            byte[] expectedBytes = Files.readAllBytes(path);
            return normalize(new String(expectedBytes, "utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getResourceStream(String name) {
        try {
            String resource = getResource(name);
            byte[] bytes = resource.getBytes("utf-8");
            return new ByteArrayInputStream(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String normalize(String str) {
        return str.trim().replaceAll("\r\n", "\n");
    }
}
