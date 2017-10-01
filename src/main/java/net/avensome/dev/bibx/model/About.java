package net.avensome.dev.bibx.model;

import net.avensome.dev.bibx.serde.types.BooleanAsIntConverter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.convert.Convert;

public class About {
    @Element(name = "language")
    private final String language;

    @Element(name = "authorised")
    @Convert(BooleanAsIntConverter.class)
    private final Boolean authorized;

    @Element(name = "name")
    private final String name;

    @Element(name = "description")
    private final String description;

    @Element(name = "shortname")
    private final String shortName;

    @Element(name = "date")
    private final String date;

    public About(
            @Element(name = "language") String language,
            @Element(name = "authorised") boolean authorised,
            @Element(name = "name") String name,
            @Element(name = "description") String description,
            @Element(name = "shortname") String shortName,
            @Element(name = "date") String date
    ) {
        this.language = language;
        this.authorized = authorised;
        this.name = name;
        this.description = description;
        this.shortName = shortName;
        this.date = date;
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
}
