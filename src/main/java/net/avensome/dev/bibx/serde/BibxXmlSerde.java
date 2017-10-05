package net.avensome.dev.bibx.serde;

import net.avensome.dev.bibx.model.Bible;
import net.avensome.dev.bibx.serde.helper.ClassAttributeRemoverVisitor;
import net.avensome.dev.bibx.serde.types.BibxRegistryMatcher;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.VisitorStrategy;
import org.simpleframework.xml.stream.Format;

import java.io.InputStream;
import java.io.OutputStream;

public class BibxXmlSerde {
    private final Persister persister;

    public BibxXmlSerde() {
        this(false);
    }

    public BibxXmlSerde(boolean prettyOutput) {
        Format format = new Format(prettyOutput ? 4 : 0, "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        persister = new Persister(
                new VisitorStrategy(new ClassAttributeRemoverVisitor(), new AnnotationStrategy()),
                new BibxRegistryMatcher(),
                format);
    }

    public Bible deserialize(InputStream stream) {
        try {
            return persister.read(Bible.class, stream);
        } catch (Exception e) {
            throw new SerdeException(e);
        }
    }

    public void serialize(Bible bible, OutputStream stream) {
        try {
            persister.write(bible, stream);
        } catch (Exception e) {
            throw new SerdeException(e);
        }
    }
}
