package net.avensome.dev.jbibx.serde.types;

import net.avensome.dev.jbibx.model.BookID;
import org.simpleframework.xml.transform.Transform;

public class BookIdTransform implements Transform<BookID> {
    @Override
    public BookID read(String value) throws Exception {
        return BookID.fromString(value);
    }

    @Override
    public String write(BookID value) throws Exception {
        return value.toString();
    }
}
