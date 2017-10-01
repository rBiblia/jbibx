package net.avensome.dev.bibx.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Root(name = "bibx")
public class Bible {
    @Element(name = "about")
    private final About about;

    @ElementList(name = "translation", entry = "book", required = false, empty = false)
    private final List<Book> books;
    private final Map<BookID, Book> booksMap;

    public Bible(
            @Element(name = "about") About about,
            @ElementList(name = "translation", entry = "book", required = false, empty = false) List<Book> books
    ) {
        this.about = about;
        this.books = Collections.unmodifiableList(new ArrayList<>(books));
        this.booksMap = Collections.unmodifiableMap(Identifiable.listToMap(books));
        if (books.size() > booksMap.size()) {
            throw new SemanticException("There are duplicate books in the list");
        }
    }

    public About getAbout() {
        return about;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(BookID id) {
        return booksMap.get(id);
    }

    public List<BookID> getBookIds() {
        return Collections.unmodifiableList(Identifiable.listToIdList(books));
    }
}
