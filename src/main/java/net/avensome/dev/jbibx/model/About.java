package net.avensome.dev.jbibx.model;

import net.avensome.dev.jbibx.serde.types.BooleanAsIntConverter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.convert.Convert;

public class About {
    @Element(name = "language", required = false)
    private final String language;

    @Element(name = "authorised", required = false)
    @Convert(BooleanAsIntConverter.class)
    private final Boolean authorized;

    @Element(name = "name", required = false)
    private final String name;

    @Element(name = "description", required = false)
    private final String description;

    @Element(name = "shortname", required = false)
    private final String shortName;

    @Element(name = "date", required = false)
    private final String date;

    @Element(name = "author", required = false)
    private final String author;

    public About(
            @Element(name = "language") String language,
            @Element(name = "authorised") boolean authorized,
            @Element(name = "name") String name,
            @Element(name = "description") String description,
            @Element(name = "shortname") String shortName,
            @Element(name = "date") String date,
            @Element(name = "author", required = false) String author
    ) {
        this.language = language == null ? "" : language;
        this.authorized = authorized;
        this.name = name == null ? "" : name;
        this.description = description == null ? "" : description;
        this.shortName = shortName == null ? "" : shortName;
        this.date = date == null ? "" : date;
        this.author = author == null ? "" : author;
    }

    public About() {
        this(null, false, null, null, null, null, null);
    }

    public String getLanguage() {
        return language;
    }

    public boolean getAuthorized() {
        return authorized;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        About about = (About) o;

        if (!language.equals(about.language)) return false;
        if (!authorized.equals(about.authorized)) return false;
        if (!name.equals(about.name)) return false;
        if (!description.equals(about.description)) return false;
        if (!shortName.equals(about.shortName)) return false;
        if (!date.equals(about.date)) return false;
        return author.equals(about.author);
    }

    @Override
    public int hashCode() {
        int result = language.hashCode();
        result = 31 * result + authorized.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
