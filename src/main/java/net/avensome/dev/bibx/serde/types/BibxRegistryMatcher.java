package net.avensome.dev.bibx.serde.types;

import net.avensome.dev.bibx.model.BookID;
import org.simpleframework.xml.transform.RegistryMatcher;

public class BibxRegistryMatcher extends RegistryMatcher {
    public BibxRegistryMatcher() {
        bind(BookID.class, BookIdTransform.class);
    }
}
