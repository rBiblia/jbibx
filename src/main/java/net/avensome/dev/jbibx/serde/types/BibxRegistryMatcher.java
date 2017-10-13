package net.avensome.dev.jbibx.serde.types;

import net.avensome.dev.jbibx.model.BookID;
import org.simpleframework.xml.transform.RegistryMatcher;

public class BibxRegistryMatcher extends RegistryMatcher {
    public BibxRegistryMatcher() {
        bind(BookID.class, BookIdTransform.class);
    }
}
