package net.avensome.dev.bibx.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.*;

@Root(name = "bibx")
public class Bible {
    @Element(name = "about")
    private final About about;

    private final Map<BookID, Book> books;

    public Bible(
            @Element(name = "about") About about,
            @ElementList(name = "translation", entry = "book", required = false, empty = false) List<Book> books
    ) {
        this.about = about;
        this.books = Collections.unmodifiableMap(Identifiable.listToMap(books));
        if (books.size() > this.books.size()) {
            throw new SemanticException("There are duplicate books in the list");
        }
    }

    public About getAbout() {
        return about;
    }

    public SortedSet<Book> getBooks() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(this.books.values()));
    }

    @ElementList(name = "translation", entry = "book", required = false, empty = false)
    private List<Book> getOrderedBooks() {
        return new ArrayList<>(getBooks());
    }

    public Book getBook(BookID id) {
        return books.get(id);
    }

    public Set<BookID> getBookIds() {
        return Collections.unmodifiableSet((books.keySet()));
    }
}
