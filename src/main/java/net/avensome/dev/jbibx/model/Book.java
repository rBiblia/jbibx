package net.avensome.dev.jbibx.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.*;

public class Book extends Identifiable<BookID> implements Comparable<Book> {
    @Attribute(name = "id")
    private final BookID id;

    private final Map<Integer, Chapter> chapters;
    private final List<Chapter> orderedChapters;

    public Book(BookID id) {
        this(id, Collections.<Chapter>emptyList());
    }

    public Book(
            @Attribute(name = "id") BookID id,
            @ElementList(inline = true, entry = "chapter", required = false, empty = false) List<Chapter> chapters
    ) {
        this.id = id;
        this.chapters = Collections.unmodifiableMap(Identifiable.listToMap(chapters));
        orderedChapters = new LinkedList<>(chapters);
        Collections.sort(orderedChapters);
    }

    public BookID getId() {
        return id;
    }

    @ElementList(inline = true, entry = "chapter", required = false, empty = false)
    public List<Chapter> getOrderedChapters() {
        return orderedChapters;
    }

    public Chapter getChapter(int number) {
        if (!chapters.containsKey(number)) {
            throw new NoSuchElementException(String.format("Book %s doesn't contain chapter %d", id, number));
        }
        return chapters.get(number);
    }

    @Override
    public int compareTo(Book that) {
        return this.id.compareTo(that.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id && chapters.equals(book.chapters);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + chapters.hashCode();
        return result;
    }
}
