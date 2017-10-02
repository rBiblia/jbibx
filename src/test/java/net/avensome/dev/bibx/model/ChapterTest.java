package net.avensome.dev.bibx.model;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ChapterTest {
    @Test
    public void createFromList() {
        List<String> verses = Arrays.asList("lorem ipsum", "dolor sit amet");
        Chapter chapter = new Chapter(1, verses);
        assertEquals(2, chapter.getOrderedVerses().size());
        assertEquals("lorem ipsum", chapter.getOrderedVerses().get(0));
        assertEquals("dolor sit amet", chapter.getOrderedVerses().get(1));
        assertEquals("lorem ipsum", chapter.getVerse(1));
        assertEquals("dolor sit amet", chapter.getVerse(2));
    }

    @Test
    public void createFromListWithNulls() {
        List<String> verses = Arrays.asList("lorem ipsum", null, "dolor sit amet");
        Chapter chapter = new Chapter(1, verses);
        assertEquals(3, chapter.getOrderedVerses().size());
        assertEquals("lorem ipsum", chapter.getOrderedVerses().get(0));
        assertNull(chapter.getOrderedVerses().get(1));
        assertEquals("dolor sit amet", chapter.getOrderedVerses().get(2));
    }

    @Test
    public void createFromMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "lorem ipsum");
        map.put(2, "dolor sit amet");
        Chapter chapter = new Chapter(1, map);
        assertEquals("lorem ipsum", chapter.getVerse(1));
        assertEquals("dolor sit amet", chapter.getVerse(2));
    }

    @Test
    public void createFromMapWithNulls() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "lorem ipsum");
        map.put(2, null);
        map.put(3, "dolor sit amet");
        Chapter chapter = new Chapter(1, map);
        assertEquals("lorem ipsum", chapter.getVerse(1));
        assertEquals("dolor sit amet", chapter.getVerse(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void nullsAreOmittedWhenCreatingFromMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "lorem ipsum");
        map.put(2, null);
        map.put(3, "dolor sit amet");
        Chapter chapter = new Chapter(1, map);
        chapter.getVerse(2);
    }
}
